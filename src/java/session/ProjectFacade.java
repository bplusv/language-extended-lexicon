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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Definition;
import model.Document;
import model.Project;
import model.Symbol;
import model.User;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Luis Salazar <bp.lusv@gmail.com>
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
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
    private Map<String, Integer> symbolsMap;
    private Pattern symbolsPattern;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Collection<Project> findAll() {
        try {
            return em.createQuery("SELECT pr FROM Project pr "
                    + "ORDER BY LOWER(pr.name) ASC;").
                    getResultList();
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Project createProject(String userId, String name) {
        try {
            if (name.isEmpty()) {
                return null;
            }
            name = name.trim();
            Project project = new Project();
            Collection<User> users = new ArrayList<User>();
            users.add(userFacade.find(userId));
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
    public Collection<Document> getDocumentCollection(String projectId) {
        try {
            return em.createQuery("SELECT do FROM Document do "
                    + "WHERE do.project = :project "
                    + "ORDER BY LOWER(do.name) ASC;").
                    setParameter("project", projectFacade.find(projectId)).
                    getResultList();
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Collection<Definition> getDefinitionCollection(String projectId) {
        try {
            return em.createQuery("SELECT DISTINCT de FROM Symbol sy "
                    + "LEFT OUTER JOIN sy.definition de "
                    + "LEFT OUTER JOIN de.classification cl "
                    + "WHERE sy.project = :project AND sy.active = TRUE "
                    + "ORDER BY cl.name, de.category.name, sy.name;").
                    setParameter("project", find(projectId)).
                    getResultList();
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Collection<Symbol> getSymbolCollection(String projectId) {
        try {
            return em.createQuery("SELECT sy FROM Symbol sy "
                    + "WHERE sy.project = :project AND sy.active = TRUE "
                    + "ORDER BY LOWER(sy.name) ASC;").
                    setParameter("project", find(projectId)).
                    getResultList();
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void initTagSymbols(String projectId) {
        try {
            Collection<Symbol> projectSymbols = getSymbolCollection(projectId);
            symbolsMap = new HashMap<String, Integer>();
            ArrayList<String> symbolNames = new ArrayList<String>();
            for (Symbol symbol : projectSymbols) {
                symbolsMap.put(symbol.getName(), symbol.getId());
                symbolNames.add(Pattern.quote(symbol.getName()));
            }
            Collections.sort(symbolNames);
            Collections.reverse(symbolNames);
            String words = StringUtils.join(symbolNames, ")|(");
            symbolsPattern = Pattern.compile("^((" + words + "))");
        } catch (Exception e) {
            context.setRollbackOnly();
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String tagSymbols(String content) {
        try {
            String result = "";
            Boolean matched;
            Matcher symbolMatcher;
            while (!content.isEmpty()) {
                symbolMatcher = symbolsPattern.matcher(content);
                matched = symbolMatcher.find();
                if (matched) {
                    result += "<a class=\"symbol\" href=\""
                            + "#!/classify?sy=" + symbolsMap.get(symbolMatcher.group(0)) + "\">"
                            + StringEscapeUtils.escapeHtml4(symbolMatcher.group(0)) + "</a>";
                    content = content.substring(symbolMatcher.group(0).length());
                } else {
                    result += StringEscapeUtils.escapeHtml4(
                            Character.toString(content.charAt(0)));
                    content = content.substring(1);
                }
            }
            return result;
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }
}