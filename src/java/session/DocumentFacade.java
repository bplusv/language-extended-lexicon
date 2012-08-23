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
import model.Symbol;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Document;
import model.Project;

/**
 *
 * @author Luis Salazar <bp.lusv@gmail.com>
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DocumentFacade extends AbstractFacade<Document> {
    @PersistenceContext(unitName = "lelPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public DocumentFacade() {
        super(Document.class);
    }
		
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Document createDocument(String projectId, String name) {
        try {
            if (name.isEmpty()) return null;
            name = name.trim();
            Document document = new Document();
            document.setName(name);
            document.setProject(projectFacade.find(projectId));
            em.persist(document);
            em.flush();
            return document;
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }  
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Document updateContent(String documentId, String content) {
        try {
            Document document = find(documentId);
            document.setContent(content);
            em.merge(document);
            em.flush();
            return document;
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Collection<Symbol> getSymbolCollection(String documentId) {
        try {
			return em.createQuery("SELECT sy FROM Symbol sy WHERE "
				+ "sy.document = :document AND sy.active = TRUE;").
				setParameter("document", documentFacade.find(documentId)).
				getResultList();
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }
}