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
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
	public Symbol createSymbol(String projectId, String userId, String documentId,
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
			symbol.setDefinition(definitionFacade.createDefinition(categoryId,
				classificationId, notion, actualIntention, futureIntention, comments));
			em.persist(symbol);
			em.flush();
			logFacade.createLog(userId, symbol.getId().toString(), "1");
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
			Collection<Symbol> synonyms = symbolFacade.findSynonyms(symbol.getId().toString());
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
			Symbol symbol = new Symbol();
			symbol.setDocument(documentFacade.find(documentId));
			symbol.setName(name);
			return symbol;
		} catch (Exception e) {
			context.setRollbackOnly();
			return null;
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Symbol updateSymbol(String userId, String symbolId,
		String categoryId, String classificationId, String notion,
		String actualIntention, String futureIntention, String comments) {
		try {
			Symbol symbol = symbolFacade.find(symbolId);
			definitionFacade.updateDefinition(
				symbol.getDefinition().getId().toString(),
				categoryId, classificationId, notion,
				actualIntention, futureIntention, comments);
			em.merge(symbol);
			em.flush();
			logFacade.createLog(userId, symbolId, "2");
			Collection<Symbol> synonyms = symbolFacade.findSynonyms(symbol.getId().toString());
			for (Symbol synonym : synonyms) {
				logFacade.createLog(userId, synonym.getId().toString(), "2");
			}
			return symbol;
		} catch (Exception e) {
			context.setRollbackOnly();
			return null;
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Symbol updateSymbolBySynonym(String userId, String symbolId, String synonymId) {
		try {
			Symbol symbol = symbolFacade.find(symbolId);
			symbol.setDefinition(symbolFacade.find(synonymId).getDefinition());
			em.merge(symbol);
			em.flush();
			logFacade.createLog(userId, symbolId, "2");
			Collection<Symbol> synonyms = symbolFacade.findSynonyms(symbol.getId().toString());
			for (Symbol synonym : synonyms) {
				logFacade.createLog(userId, synonym.getId().toString(), "2");
			}
			return symbol;
		} catch (Exception e) {
			context.setRollbackOnly();
			return null;
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Log getLastLog(String symbolId) {
		try {
			return (Log) em.createQuery("SELECT lo FROM Log lo WHERE "
				+ "lo.symbol = :symbol ORDER BY lo.date DESC;").
				setParameter("symbol", symbolFacade.find(symbolId)).
				setMaxResults(1).
				getSingleResult();
		} catch (Exception e) {
			context.setRollbackOnly();
			return null;
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection<Symbol> findSynonyms(String symbolId) {
		try {
			Symbol symbol = symbolFacade.find(symbolId);
			return em.createQuery("SELECT sy FROM Symbol sy WHERE "
				+ "sy <> :symbol AND sy.definition = :definition AND "
				+ "sy.project = :project;").
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
				+ "sy.document = :document AND sy.name = :name;").
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
				+ ":classificationName = '%')) AND LOWER(sy.name) LIKE :name "
				+ "ORDER BY LOWER(sy.name) ASC;").
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