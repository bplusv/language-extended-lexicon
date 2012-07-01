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

import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.*;

/**
 *
 * @author Luis Salazar <bp.lusv@gmail.com>
 */
@Stateless
public class SymbolFacade extends AbstractFacade<Symbol> {
    @PersistenceContext(unitName = "lelPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @EJB private EventFacade eventFacade;
    @EJB private CategoryFacade categoryFacade;
    @EJB private ClassificationFacade classificationFacade;
    
    public SymbolFacade() {
        super(Symbol.class);
    }
    
    private Definition createDefinition(Category category, Classification classification,
            String notion, String actualIntention, String futureIntention,String comments) {
        Definition definition = new Definition();
        definition.setCategory(category);
        definition.setClassification(classification);
        definition.setNotion(notion);
        definition.setActualIntention(actualIntention);
        definition.setFutureIntention(futureIntention);
        definition.setComments(comments);
        em.persist(definition);
        em.flush();
        return definition;
    }
    
    private Definition updateDefinition(Definition definition, Category category, Classification classification, String notion,
            String actualIntention, String futureIntention, String comments) {
        definition.setCategory(category);
        definition.setClassification(classification);
        definition.setNotion(notion);
        definition.setActualIntention(actualIntention);
        definition.setFutureIntention(futureIntention);
        definition.setComments(comments);
        em.merge(definition);
        em.flush();
        return definition;
    }
    
    private Log createLog(User user, Symbol symbol, Event event) {
        Log log = new Log();
        log.setUser(user);
        log.setSymbol(symbol);
        log.setEvent(event);
        log.setDate(new Date());
        em.persist(log);
        em.flush();
        return log;
    }
    
    public Symbol createSymbol(Project project, User user, Document document, String name, Category category,
            Classification classification, String notion, String actualIntention,
            String futureIntention, String comments) throws Exception {
            
        if (name.isEmpty()) throw new Exception("Empty document name");
        name = name.trim();
        
        Definition definition = createDefinition(category, classification,
            notion, actualIntention, futureIntention, comments);
        
        Symbol symbol = new Symbol();
        symbol.setProject(project);
        symbol.setDocument(document);
        symbol.setName(name);
        symbol.setDefinition(definition);
        em.persist(symbol);
        em.flush();

        createLog(user, symbol, eventFacade.find(1));
        return symbol;
    }
    
    public Symbol updateSymbol(User user, Integer symbolId, Category category,
            Classification classification, String notion, String actualIntention,
            String futureIntention, String comments) {
       
        Symbol symbol = this.find(symbolId);
        Definition definition = symbol.getDefinition();
        
        definition.setCategory(category);
        // category is 'general'?
        if (category.getId() == 1) {
            classification = null;
            actualIntention = null;
            futureIntention = null;
        }
        
        updateDefinition(definition, category, classification, notion,
            actualIntention, futureIntention, comments);
        
        createLog(user, symbol, eventFacade.find(2));
        return symbol;
    }
    
    public Collection<Symbol> findSynonyms(Integer symbolId) {
        Symbol symbol = this.find(symbolId);
        return em.createQuery("SELECT sy FROM Symbol sy WHERE sy <> :symbol AND sy.definition = :definition;").
                setParameter("symbol", symbol).
                setParameter("definition", symbol.getDefinition()).
                getResultList();
    }
    
    public Log findLastLog(Symbol symbol) throws Exception {
        return (Log) em.createQuery("SELECT lo FROM Log lo WHERE lo.symbol = :symbol "
                + "ORDER BY lo.date DESC;").
                setParameter("symbol", symbol).
                setMaxResults(1).
                getSingleResult();
    }

    public Collection<Symbol> findByFilters(Project project, String categoryId, String classificationId, String name) throws Exception {
        String categoryName;
        if (categoryId == null || categoryId.isEmpty()) {
            categoryName = "%";
        } else {
            Category category = categoryFacade.find(Integer.parseInt(categoryId));
            categoryName = category.getName();
        }
        String classificationName;
        if (classificationId == null || classificationId.isEmpty()) {
            classificationName = "%";
        } else {
            Classification classification = classificationFacade.find(Integer.parseInt(classificationId));
            classificationName = classification.getName();
        }
        return em.createQuery("SELECT sy FROM Symbol sy LEFT JOIN sy.definition de LEFT JOIN de.category ca "
                + "LEFT JOIN de.classification cl WHERE sy.project = :project AND "
                + "ca.name LIKE :categoryName AND (cl.name LIKE :classificationName OR "
                + "(cl.name IS NULL AND :classificationName = '%')) AND "
                + "LOWER(sy.name) LIKE :name ORDER BY LOWER(sy.name) ASC;").
                setParameter("project", project).
                setParameter("categoryName", categoryName).
                setParameter("classificationName", classificationName).
                setParameter("name", "%"+name.toLowerCase()+"%").
                getResultList();
    }

    public Symbol findByDocumentAndName(Document document, String name) {
        return (Symbol) em.createQuery("SELECT sy FROM Symbol sy WHERE sy.document = :document AND sy.name = :name;").
                setParameter("document", document).
                setParameter("name", name).
                getSingleResult();
    }
    
}