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

import entity.*;
import session.*;
import java.util.Collection;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Luis Salazar <bp.lusv@gmail.com>
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DocumentManager {

    @PersistenceContext(unitName = "lelPU")
    private EntityManager em;
    @Resource
    private SessionContext context;
    
    @EJB private DocumentFacade documentFacade;
    @EJB private ProjectFacade projectFacade;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String getTaggedDataByDoc(String documentParam) {
        try {
            Document document = documentFacade.find(Integer.parseInt(documentParam));
            Collection<Symbol> symbols = document.getSymbolCollection();
            String taggedData = document.getData();
            for (Symbol symbol : symbols) {
                taggedData = taggedData.replaceAll(symbol.getName(), "<a href=\"classify?co=" + symbol.getId() + "\" contenteditable=\"false\">" + symbol.getName() + "</a>");
            }
            return taggedData;
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Document getDocument(String documentParam) {
        try {
            return documentFacade.find(Integer.parseInt(documentParam));
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Document createDocument(String nameParam, String projectParam) {
        try {
            String name = nameParam.trim().isEmpty() ? null : nameParam.trim();
            Project project = projectFacade.find(Integer.parseInt(projectParam));
            
            Document document = new Document();
            document.setName(name);
            document.setProject(project);
            em.persist(document);
            em.flush();
            return document;
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Document updateDocument(String documentParam, String dataParam) {
        try {
            Document document = documentFacade.find(Integer.parseInt(documentParam));
            String data = dataParam;
            
            document.setData(data);
            em.merge(document);
            em.flush();
            return document;
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }
}
