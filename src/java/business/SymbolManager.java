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

import session.CategoryFacade;
import session.DocumentFacade;
import session.EventFacade;
import session.SymbolFacade;
import session.ProjectFacade;
import session.UserFacade;
import session.ClassificationFacade;
import entity.*;
import java.util.Collection;
import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import session.*;

/**
 *
 * @author Luis Salazar <bp.lusv@gmail.com>
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SymbolManager {

    @PersistenceContext(unitName = "lelPU")
    private EntityManager em;
    @Resource
    private SessionContext context;
    @EJB
    private DocumentFacade documentFacade;
    @EJB
    private SymbolFacade symbolFacade;
    @EJB
    private CategoryFacade categoryFacade;
    @EJB
    private ClassificationFacade classificationFacade;
    @EJB
    private UserFacade userFacade;
    @EJB
    private ProjectFacade projectFacade;
    @EJB
    private EventFacade eventFacade;

    private Collection<Symbol> findSymbolSynonyms(Symbol symbol) {
        return em.createQuery("SELECT sy FROM Symbol sy WHERE sy <> :symbol AND sy.definition = :definition;").
                setParameter("symbol", symbol).
                setParameter("definition", symbol.getDefinition()).
                getResultList();
    }
    
    private Symbol findSymbolByDocAndName(Document document, String name) {
        return (Symbol) em.createQuery("SELECT sy FROM Symbol sy WHERE sy.document = :document AND sy.name = :name;").
                setParameter("document", document).
                setParameter("name", name).
                getSingleResult();
    }

    private Log findLastLogBySymbol(Symbol symbol) throws Exception {
        return (Log) em.createQuery("SELECT lo FROM Log lo WHERE lo.symbol = :symbol "
                + "ORDER BY lo.date DESC;").
                setParameter("symbol", symbol).
                setMaxResults(1).
                getSingleResult();
    }

    private Collection<Symbol> findSymbolsByFilters(Project project, String classificationName, String categoryName, String symbolName) throws Exception {
        return em.createQuery("SELECT sy FROM Symbol sy LEFT JOIN sy.definition de LEFT JOIN de.category ca "
                + "LEFT JOIN de.classification cl WHERE sy.project = :project AND "
                + "ca.name LIKE :categoryName AND (cl.name LIKE :classificationName OR "
                + "(cl.name IS NULL AND :classificationName = '%')) AND "
                + "LOWER(sy.name) LIKE :symbolName ORDER BY LOWER(sy.name) ASC;").
                setParameter("project", project).
                setParameter("categoryName", categoryName).
                setParameter("classificationName", classificationName).
                setParameter("symbolName", symbolName).
                getResultList();
    }

    private Log logEvent(User user, Symbol symbol, Event event) throws Exception {
        Log log = new Log();
        log.setUser(user);
        log.setSymbol(symbol);
        log.setEvent(event);
        log.setDate(new Date());
        em.persist(log);
        em.flush();
        return log;
    }

    private Definition addDefinition(Category category, Classification classification,
            String notion, String actualIntention, String futureIntention, String comments) throws Exception {
        Definition definition = new Definition();
        definition.setCategory(category);
        // category is 'general'
        if (category.getId() == 1) {
            classification = null;
            actualIntention = null;
            futureIntention = null;
        }
        definition.setNotion(notion);
        definition.setClassification(classification);
        definition.setActualIntention(actualIntention);
        definition.setFutureIntention(futureIntention);
        definition.setComments(comments);
        em.persist(definition);
        em.flush();
        return definition;
    }

    private Symbol addSymbol(Project project, Document document, String name, Definition definition) throws Exception {
        Symbol symbol = new Symbol();
        symbol.setProject(project);
        symbol.setDocument(document);
        symbol.setName(name);
        symbol.setDefinition(definition);
        em.persist(symbol);
        em.flush();
        return symbol;
    }

    public Symbol createPossibleSymbol(String symbolNameParam, String documentParam) {
        try {
            String name = symbolNameParam.trim().isEmpty() ? null : symbolNameParam.trim();
            Document document = documentFacade.find(Integer.parseInt(documentParam));

            Symbol symbol = new Symbol();
            symbol.setName(name);
            symbol.setDocument(document);
            return symbol;
        } catch (Exception ex) {
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Log getLog(String symbolParam) {
        try {
            Symbol symbol = symbolFacade.find(Integer.parseInt(symbolParam));
            return findLastLogBySymbol(symbol);
        } catch (Exception ex) {
            context.setRollbackOnly();
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Collection<Symbol> getSymbolSynonyms(String symbolParam) {
        try {
            Symbol symbol = symbolFacade.find(Integer.parseInt(symbolParam));
            Collection<Symbol> synonyms = findSymbolSynonyms(symbol);
            return synonyms;
        } catch (Exception ex) {
            context.setRollbackOnly();
            return null;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Symbol getSymbolByDocAndName(String documentParam, String symbolNameParam) {
        try {
            Document document = documentFacade.find(Integer.parseInt(documentParam));
            String name = symbolNameParam;
            Symbol symbol = findSymbolByDocAndName(document, name);
            return symbol;
        } catch (Exception ex) {
            context.setRollbackOnly();
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Symbol getSymbol(String symbolParam) {
        try {
            return symbolFacade.find(Integer.parseInt(symbolParam));
        } catch (Exception ex) {
            context.setRollbackOnly();
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Collection<Symbol> getSymbolsByFilters(String projectParam, String classificationParam,
            String categoryParam, String symbolParam) {
        try {
            Project project = projectFacade.find(Integer.parseInt(projectParam));
            String classificationName = "%";
            if (classificationParam != null) {
                try {
                    Classification classification = classificationFacade.find(Integer.parseInt(classificationParam));
                    if (classification != null) {
                        classificationName = classification.getName();
                    }
                } catch (Exception ex) {
                }
            }
            String categoryName = "%";
            if (categoryParam != null) {
                try {
                    Category category = categoryFacade.find(Integer.parseInt(categoryParam));
                    if (category != null) {
                        categoryName = category.getName();
                    }
                } catch (Exception ex) {
                }
            }
            String symbolName = "%";
            if (symbolParam != null) {
                symbolName = "%" + symbolParam.toLowerCase() + "%";
            }
            Collection<Symbol> symbols = this.findSymbolsByFilters(project, classificationName, categoryName, symbolName);
            return symbols;
        } catch (Exception ex) {
            context.setRollbackOnly();
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Symbol updateSymbol(String userParam, String symbolParam, String categoryParam,
            String classificationParam, String notionParam, String actualIntentionParam,
            String futureIntentionParam, String commentsParam) {
        try {
            User user = userFacade.find(Integer.parseInt(userParam));
            Symbol symbol = symbolFacade.find(Integer.parseInt(symbolParam));
            Definition definition = symbol.getDefinition();
            Category category = categoryFacade.find(Integer.parseInt(categoryParam));
            Classification classification = classificationFacade.find(Integer.parseInt(classificationParam));
            String notion = notionParam;
            String actualIntention = actualIntentionParam;
            String futureIntention = futureIntentionParam;
            String comments = commentsParam;

            definition.setCategory(category);
            // category is 'general'
            if (category.getId() == 1) {
                classification = null;
                actualIntention = null;
                futureIntention = null;
            }
            definition.setNotion(notion);
            definition.setClassification(classification);
            definition.setActualIntention(actualIntention);
            definition.setFutureIntention(futureIntention);
            definition.setComments(comments);
            em.merge(definition);
            em.flush();
            logEvent(user, symbol, eventFacade.find(2));
            return symbol;
        } catch (Exception ex) {
            context.setRollbackOnly();
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Symbol createSymbol(String userParam, String projectParam, String documentParam, String nameParam, String categoryParam,
            String classificationParam, String notionParam, String actualIntentionParam,
            String futureIntentionParam, String commentsParam) {
        try {
            User user = userFacade.find(Integer.parseInt(userParam));
            Project project = projectFacade.find(Integer.parseInt(projectParam));
            Document document = documentFacade.find(Integer.parseInt(documentParam));
            String name = nameParam.trim().isEmpty() ? null : nameParam.trim();
            Category category = categoryFacade.find(Integer.parseInt(categoryParam));
            Classification classification = classificationFacade.find(Integer.parseInt(classificationParam));
            String notion = notionParam;
            String actualIntention = actualIntentionParam;
            String futureIntention = futureIntentionParam;
            String comments = commentsParam;
            Definition definition = addDefinition(category, classification, notion,
                    actualIntention, futureIntention, comments);
            Symbol symbol = addSymbol(project, document, name, definition);
            logEvent(user, symbol, eventFacade.find(1));
            return symbol;
        } catch (Exception ex) {
            context.setRollbackOnly();
            return null;
        }
    }
}