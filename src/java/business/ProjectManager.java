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
package business;

import entity.Document;
import entity.Project;
import entity.Symbol;
import entity.User;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import session.ProjectFacade;
import session.UserFacade;

/**
 *
 * @author Luis Salazar <bp.lusv@gmail.com>
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProjectManager {

    @PersistenceContext(unitName = "lelPU")
    private EntityManager em;
    @Resource
    private SessionContext context;
    
    @EJB private ProjectFacade projectFacade;
    @EJB private UserFacade userFacade;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Project getProject(String projectParam) {
        try {
            Project project = projectFacade.find(Integer.parseInt(projectParam));
            return project;
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Project createProject(String nameParam, String userParam) {
        try {
            String name = nameParam.trim().isEmpty() ? null : nameParam.trim();
            User user = userFacade.find(Integer.parseInt(userParam));
            Project project = new Project();
            Collection<User> users = new ArrayList<User>();
            users.add(user);
            project.setName(name);
            project.setUserCollection(users);
            em.persist(project);
            em.flush();
            return project;
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Collection<Document> getProjectDocuments(String projectParam) {
        try {
            Project project = projectFacade.find(Integer.parseInt(projectParam));
            em.refresh(project);
            Collection<Document> documents = project.getDocumentCollection();
            em.flush();
            return documents;
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Collection<Symbol> getProjectSymbols(String projectParam) {
        try {
            Project project = projectFacade.find(Integer.parseInt(projectParam));
            em.refresh(project);
            Collection<Symbol> symbols = project.getSymbolCollection();
            em.flush();
            return symbols;
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }
}
