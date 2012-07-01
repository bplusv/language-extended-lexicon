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

import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.Stateless;
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
public class ProjectFacade extends AbstractFacade<Project> {
    @PersistenceContext(unitName = "lelPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public ProjectFacade() {
        super(Project.class);
    }
    
    public Collection<Document> getDocumentCollection(Integer projectId) {
        Project project = this.find(projectId);
        em.refresh(project);
        return project.getDocumentCollection();
    }
    
    public Collection<Symbol> getSymbolCollection(Integer projectId) {
        Project project = this.find(projectId);
        em.refresh(project);
        return project.getSymbolCollection();
    }
        
    public Project createProject(String projectName, User user) throws Exception {
        if (projectName.isEmpty())
            throw new Exception("Empty project name");
        projectName = projectName.trim();
        Project project = new Project();
        Collection<User> users = new ArrayList<User>();
        users.add(user);
        project.setName(projectName);
        project.setUserCollection(users);
        em.persist(project);
        em.flush();
        return project;
    }

}