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

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Definition;

/**
 *
 * @author Luis Salazar <bp.lusv@gmail.com>
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DefinitionFacade extends AbstractFacade<Definition> {
    @PersistenceContext(unitName = "lelPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public DefinitionFacade() {
        super(Definition.class);
    }
    
    private void fillDefinition(Definition definition, String categoryId, String classificationId,
            String notion, String actualIntention, String futureIntention,String comments) {   
        definition.setCategory(categoryFacade.find(categoryId));
        if ("1".equals(categoryId)) {
            definition.setClassification(null);
            definition.setActualIntention(null);
            definition.setFutureIntention(null);
        } else {
            definition.setClassification(classificationFacade.find(classificationId));
            definition.setActualIntention(actualIntention);
            definition.setFutureIntention(futureIntention);
        }
        definition.setNotion(notion);
        definition.setComments(comments);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Definition createDefinition(String categoryId, String classificationId,
            String notion, String actualIntention, String futureIntention, String comments) {
        try {
            Definition definition = new Definition();
            fillDefinition(definition, categoryId, classificationId,
                notion, actualIntention, futureIntention, comments);
            em.persist(definition);
            em.flush();
            return definition;
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Definition updateDefinition(String definitionId, String categoryId, 
            String classificationId, String notion, String actualIntention, 
            String futureIntention, String comments) {
        try {
            Definition definition = find(definitionId);
            fillDefinition(definition, categoryId, classificationId,
                notion, actualIntention, futureIntention, comments);
            em.merge(definition);
            em.flush();
            return definition;
            } catch (Exception e) {
                context.setRollbackOnly();
                return null;
            }
        }
}