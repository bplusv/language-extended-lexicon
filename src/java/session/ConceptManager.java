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

import entity.*;
import java.util.List;
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
public class ConceptManager {

    @PersistenceContext(unitName = "lelPU")
    private EntityManager em;
    @Resource
    private SessionContext context;
    
    @EJB private DocumentFacade documentFacade;
    @EJB private ConceptFacade conceptFacade;
    @EJB private CategoryFacade categoryFacade;
    @EJB private ClassificationFacade classificationFacade;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Concept> getConcepts(String classificationParam, String categoryParam) {
        try {
            Classification classification = classificationFacade.find(Integer.parseInt(classificationParam));
            Category category = categoryFacade.find(Integer.parseInt(categoryParam));
            List<Concept> concepts = conceptFacade.findByFilters(classification, category);
            return concepts;
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int createConcept(String nameParam, String documentParam, String categoryParam, 
            String classificationParam, String notionParam, String actualIntentionParam, 
            String futureIntentionParam, String commentsParam) {
        try {
            String name = nameParam;
            Document document = documentFacade.find(Integer.parseInt(documentParam));
            Category category = categoryFacade.find(Integer.parseInt(categoryParam));
            Classification classification = classificationFacade.find(Integer.parseInt(classificationParam));
            String notion = notionParam;
            String actualIntention = actualIntentionParam;
            String futureIntention = futureIntentionParam;
            String comments = commentsParam;
            
            Definition definition = addDefinition(category, classification, notion, 
                    actualIntention, futureIntention, comments);
            Concept concept = addConcept(definition, document, name);
            return concept.getId();
        } catch (Exception e) {
            context.setRollbackOnly();
            return -1;
        }
    }

    private Definition addDefinition(Category category, Classification classification, 
            String notion, String actualIntention, String futureIntention, String comments) {
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

    private Concept addConcept(Definition definition, Document document, String name) {
        Concept concept = new Concept();
        concept.setDefinition(definition);
        concept.setDocument(document);
        concept.setName(name);
        
        em.persist(concept);
        em.flush();
        return concept;
    }
}
