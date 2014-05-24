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
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.stream.events.Comment;
import model.Document;
import model.Log;
import model.Symbol;

/**
 *
 * @author Luis Salazar <bp.lusv@gmail.com>
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SymbolFacade extends AbstractFacade<Symbol> {

    @PersistenceContext(unitName = "lelPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SymbolFacade() {
        super(Symbol.class);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Symbol userFindSymbol(String loggedUserId, String symbolId) {
        try {
            if (!userAccessManager.hasSymbolAccess(loggedUserId, symbolId)) {
                return null;
            }
            return symbolFacade.find(symbolId);
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }
        
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Symbol createSymbol(String loggedId, String projectId, String documentId,
            String name, String categoryId, String classificationId, String notion,
            String actualIntention, String futureIntention, String comments) {
        try {
            if (name.isEmpty()) {
                return null;
            }
            name = name.trim();
            Symbol symbol = new Symbol();
            symbol.setProject(projectFacade.find(projectId));
            symbol.setDocument(documentFacade.find(documentId));
            symbol.setName(name);
            symbol.setActive(true);
            symbol.setDefinition(definitionFacade.createDefinition(loggedId, categoryId,
                    classificationId, notion, actualIntention, futureIntention, comments));
            em.persist(symbol);
            em.flush();
            logFacade.createLog(loggedId, symbol.getId().toString(), "1");
            return symbol;
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Symbol createSymbolBySynonym(String projectId, String userId,
            String documentId, String name, String synonymId) {
        try {
            if (name.isEmpty()) {
                return null;
            }
            name = name.trim();
            Symbol symbol = new Symbol();
            symbol.setProject(projectFacade.find(projectId));
            symbol.setDocument(documentFacade.find(documentId));
            symbol.setName(name);
            symbol.setActive(true);
            symbol.setDefinition(symbolFacade.find(synonymId).getDefinition());
            em.persist(symbol);
            em.flush();
            logFacade.createLog(userId, symbol.getId().toString(), "1");
            Collection<Symbol> synonyms = symbolFacade.getSynonyms(symbol.getId().toString());
            for (Symbol synonym : synonyms) {
                logFacade.createLog(userId, synonym.getId().toString(), "1");
            }
            return symbol;
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Symbol createPossibleSymbol(String documentId, String name) {
        try {
            Document document = documentFacade.find(documentId);
            if (document == null || name.isEmpty()) {
                return null;
            }
            Symbol symbol = new Symbol();
            symbol.setDocument(document);
            symbol.setName(name);
            return symbol;
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Symbol removeSymbol(String loggedUserId, String symbolId) {
        try {
            if (!userAccessManager.hasSymbolAccess(loggedUserId, symbolId)) {
                return null;
            }
            Symbol symbol = symbolFacade.find(symbolId);
            symbol.setActive(false);
            em.merge(symbol);
            em.flush();
            return symbol;
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Symbol updateSymbol(String loggedUserId, String symbolId,
            String categoryId, String classificationId, String notion,
            String actualIntention, String futureIntention, String comment) {
        try {
            if (!userAccessManager.hasSymbolAccess(loggedUserId, symbolId)) {
                return null;
            }
            Symbol symbol = symbolFacade.find(symbolId);
            definitionFacade.updateDefinition(loggedUserId,
                    symbol.getDefinition().getId().toString(),
                    categoryId, classificationId, notion,
                    actualIntention, futureIntention, comment);
            em.merge(symbol);
            em.flush();
            logFacade.createLog(loggedUserId, symbolId, "2");
            Collection<Symbol> synonyms = symbolFacade.getSynonyms(symbol.getId().toString());
            for (Symbol synonym : synonyms) {
                logFacade.createLog(loggedUserId, synonym.getId().toString(), "2");
            }
            return symbol;
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Symbol updateSymbolBySynonym(String loggedUserId, String symbolId, String synonymId) {
        try {
            if (!userAccessManager.hasSymbolAccess(loggedUserId, symbolId)) {
                return null;
            }
            Symbol symbol = symbolFacade.find(symbolId);
            symbol.setDefinition(symbolFacade.find(synonymId).getDefinition());
            em.merge(symbol);
            em.flush();
            logFacade.createLog(loggedUserId, symbolId, "2");
            Collection<Symbol> synonyms = symbolFacade.getSynonyms(symbol.getId().toString());
            for (Symbol synonym : synonyms) {
                logFacade.createLog(loggedUserId, synonym.getId().toString(), "2");
            }
            return symbol;
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Symbol leaveSynonymsGroup(String loggedUserId, String symbolId, String categoryId, String classificationId) {
        try {
            if (!userAccessManager.hasSymbolAccess(loggedUserId, symbolId)) {
                return null;
            }
            Symbol symbol = symbolFacade.find(symbolId);
            symbol.setDefinition(definitionFacade.createDefinition(loggedUserId,
                    categoryId, classificationId, "", "", "", ""));
            em.merge(symbol);
            em.flush();
            logFacade.createLog(loggedUserId, symbolId, "2");
            Collection<Symbol> synonyms = symbolFacade.getSynonyms(symbol.getId().toString());
            for (Symbol synonym : synonyms) {
                logFacade.createLog(loggedUserId, synonym.getId().toString(), "2");
            }
            return symbol;
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Collection<Comment> getCommentCollection(String symbolId) {
        try {
            return em.createQuery("SELECT co FROM Comment co, Symbol sy "
                    + "WHERE sy = :symbol AND co MEMBER OF sy.definition.commentCollection "
                    + "ORDER BY co.date DESC").
                    setParameter("symbol", symbolFacade.find(symbolId)).
                    getResultList();
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Log getLastLog(String symbolId) {
        try {
            return (Log) em.createQuery("SELECT lo FROM Log lo WHERE "
                    + "lo.symbol = :symbol ORDER BY lo.date DESC").
                    setParameter("symbol", symbolFacade.find(symbolId)).
                    setMaxResults(1).
                    getSingleResult();
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Collection<Symbol> getSynonymsGroup(String symbolId) {
        try {
            Symbol symbol = symbolFacade.find(symbolId);
            return em.createQuery("SELECT sy FROM Symbol sy WHERE "
                    + "sy.definition = :definition AND "
                    + "sy.project = :project AND sy.active = TRUE "
                    + "ORDER BY LOWER(sy.name)").
                    setParameter("project", symbol.getProject()).
                    setParameter("definition", symbol.getDefinition()).
                    getResultList();
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Collection<Symbol> getSynonyms(String symbolId) {
        try {
            Symbol symbol = symbolFacade.find(symbolId);
            return em.createQuery("SELECT sy FROM Symbol sy WHERE "
                    + "sy <> :symbol AND sy.definition = :definition "
                    + "AND sy.project = :project AND sy.active = TRUE "
                    + "ORDER BY LOWER(sy.name)").
                    setParameter("symbol", symbol).
                    setParameter("project", symbol.getProject()).
                    setParameter("definition", symbol.getDefinition()).
                    getResultList();
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Symbol findByDocumentAndName(String documentId, String name) {
        try {
            return (Symbol) em.createQuery("SELECT sy FROM Symbol sy WHERE "
                    + "sy.document = :document AND sy.name = :name AND "
                    + "sy.active = TRUE").
                    setParameter("document", documentFacade.find(documentId)).
                    setParameter("name", name).
                    getSingleResult();
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Collection<Symbol> findByFilters(String projectId, String categoryId, String classificationId, String name) {
        try {
            return em.createQuery("SELECT sy FROM Symbol sy LEFT JOIN sy.definition de "
                    + "INNER JOIN de.category ca LEFT JOIN de.classification cl WHERE "
                    + "sy.project = :project AND ca.name LIKE :categoryName AND "
                    + "(cl.name LIKE :classificationName OR (cl.name IS NULL AND "
                    + ":classificationName = '%')) AND LOWER(sy.name) LIKE :name AND "
                    + "sy.active = TRUE ORDER BY LOWER(sy.name)").
                    setParameter("project", projectFacade.find(projectId)).
                    setParameter("categoryName", !"".equals(categoryId)
                    ? (String) categoryFacade.find(categoryId).getName() : "%").
                    setParameter("classificationName", !"".equals(classificationId)
                    ? (String) classificationFacade.find(classificationId).getName() : "%").
                    setParameter("name", "%" + name.toLowerCase() + "%").
                    getResultList();
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }
}