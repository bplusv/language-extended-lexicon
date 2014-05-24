/*
 * The MIT License
 *
 * Copyright 2012 Luis Salazar <bp.lusv@gmail.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package session;

import java.util.Collection;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Document;
import model.Project;
import model.Symbol;
import model.User;

/**
 *
 * @author Luis Salazar <bp.lusv@gmail.com>
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserAccessManager extends AbstractFacade<User> {

    @PersistenceContext(unitName = "lelPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserAccessManager() {
        super(User.class);
    }
            
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Boolean hasProjectAccess(String userId, String projectId) {
        try {
            Collection<Project> projects = getUserAccesibleProjects(userId);
            Project project = projectFacade.find(projectId);
            return projects.contains(project);
        } catch (Exception e) {
            return false;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Boolean hasDocumentAccess(String userId, String documentId) {
        try {
            Collection<Project> projects = getUserAccesibleProjects(userId);
            Document document = documentFacade.find(documentId);
            return projects.contains(document.getProject());
        } catch (Exception e) {
            return false;
        }
    }
        
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Boolean hasSymbolAccess(String userId, String symbolId) {
        try {
            Collection<Project> projects = getUserAccesibleProjects(userId);
            Symbol symbol = symbolFacade.find(symbolId);
            return projects.contains(symbol.getProject());
        } catch (Exception e) {
            return false;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Boolean isProjectOwner(String userId, String projectId) {
        try {
            User user = userFacade.find(userId);
            Project project = projectFacade.find(projectId);
            return project.getOwner().equals(user);
        } catch (Exception e) {
            return false;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Boolean isProjectOwnerByName(String username, String projectId) {
        try {
            User user = userFacade.findByName(username);
            Project project = projectFacade.find(projectId);
            return project.getOwner().equals(user);
        } catch (Exception e) {
            return false;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Collection<Project> getUserAccesibleProjects(String loggedUserId) {
        try {
            User user = userFacade.find(loggedUserId);
            return em.createQuery("SELECT DISTINCT pr FROM Project pr "
                    + "LEFT OUTER JOIN pr.userCollection us "
                    + "WHERE pr.active = TRUE AND "
                    + "(us = :user OR pr.owner = :user) "
                    + "ORDER BY LOWER(pr.name)").
                    setParameter("user", user).
                    getResultList();
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }
}