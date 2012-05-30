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
public class ConceptManager {

    @PersistenceContext(unitName = "lelPU")
    private EntityManager em;
    @Resource
    private SessionContext context;
    
    @EJB private DocumentFacade documentFacade;
    @EJB private ConceptFacade conceptFacade;
    @EJB private CategoryFacade categoryFacade;
    @EJB private ClassificationFacade classificationFacade;
    @EJB private UserFacade userFacade;
    @EJB private ProjectFacade projectFacade;
    @EJB private EventFacade eventFacade;
    
    private Concept findConceptByDocAndName(Document document, String name) {
        return (Concept) em.createQuery("SELECT co FROM Concept co WHERE co.document = :document AND co.name = :name;").
                setParameter("document", document).
                setParameter("name", name).
                getSingleResult();
    }
    
    private Log findLastLogByConcept(Concept concept) throws Exception {
        return (Log) em.createQuery("SELECT lo FROM Log lo WHERE lo.concept = :concept "
                + "ORDER BY lo.date DESC;").
                setParameter("concept", concept).
                setMaxResults(1).
                getSingleResult();
    }

    private Collection<Concept> findConceptsByFilters(Project project, String classificationName, String categoryName, String conceptName) throws Exception {
        return em.createQuery("SELECT co FROM Concept co, Definition de, Classification cl, Category ca WHERE "
                + "co.definition = de AND de.classification = cl AND de.category = ca AND co.project = :project AND "
                + "cl.name LIKE :classificationName AND ca.name LIKE :categoryName AND "
                + "LOWER(co.name) LIKE :conceptName ORDER BY LOWER(co.name) ASC;").
                setParameter("project", project).
                setParameter("classificationName", classificationName).
                setParameter("categoryName", categoryName).
                setParameter("conceptName", conceptName).
                getResultList();
    }
        
    private Log logEvent(User user, Concept concept, Event event) throws Exception {
        Log log = new Log();
        log.setUser(user);
        log.setConcept(concept);
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
        definition.setClassification(classification);
        definition.setNotion(notion);
        definition.setActualIntention(actualIntention);
        definition.setFutureIntention(futureIntention);
        definition.setComments(comments);
        em.persist(definition);
        em.flush();
        return definition;
    }

    private Concept addConcept(Project project, Document document, String name, Definition definition) throws Exception {
        Concept concept = new Concept();
        concept.setProject(project);
        concept.setDocument(document);
        concept.setName(name);
        concept.setDefinition(definition);
        em.persist(concept);
        em.flush();
        return concept;
    }
    
    public Concept createPossibleConcept(String nameParam, String documentParam) {
        try {
            String name = nameParam.trim().isEmpty() ? null : nameParam.trim();
            Document document = documentFacade.find(Integer.parseInt(documentParam));
            
            Concept concept = new Concept();
            concept.setName(name);
            concept.setDocument(document);
            return concept;
        } catch (Exception ex) {
            return null;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Log getLog(String conceptParam) {
        try {
            Concept concept = conceptFacade.find(Integer.parseInt(conceptParam));
            return findLastLogByConcept(concept);
        } catch (Exception ex) {
            context.setRollbackOnly();
            return null;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Concept getConceptByDocAndName(String documentParam, String nameParam) {
        try {
            Document document = documentFacade.find(Integer.parseInt(documentParam));
            String name = nameParam;
            Concept concept = findConceptByDocAndName(document, name);
            return concept;
        } catch (Exception ex) {
            context.setRollbackOnly();
            return null;
        }
    }

        
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Concept getConcept(String conceptParam) {
        try {
            return conceptFacade.find(Integer.parseInt(conceptParam));
        } catch (Exception ex) {
            context.setRollbackOnly();
            return null;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Collection<Concept> getConceptsByFilters(String projectParam, String classificationParam,
                                                    String categoryParam, String conceptParam) {
        try {
            Project project = projectFacade.find(Integer.parseInt(projectParam));
            String classificationName = "%";
            if (classificationParam != null) {
                try {
                    Classification classification = classificationFacade.find(Integer.parseInt(classificationParam));
                    if (classification != null) classificationName = classification.getName();
                } catch (Exception ex) {}
            }         
            String categoryName = "%";
            if (categoryParam != null) {
                try {
                    Category category = categoryFacade.find(Integer.parseInt(categoryParam));
                    if (category != null) categoryName = category.getName();
                } catch (Exception ex) {}
            }
            String conceptName = "%";
            if (conceptParam != null) {
                conceptName = "%"+conceptParam.toLowerCase()+"%";
            }
            Collection<Concept> concepts = this.findConceptsByFilters(project, classificationName, categoryName, conceptName);
            return concepts;
        } catch (Exception ex) {
            context.setRollbackOnly();
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Concept updateConcept(String userParam, String conceptParam, String categoryParam, 
            String classificationParam, String notionParam, String actualIntentionParam, 
            String futureIntentionParam, String commentsParam) {
        try {
            User user = userFacade.find(Integer.parseInt(userParam));
            Concept concept = conceptFacade.find(Integer.parseInt(conceptParam));
            Definition definition = concept.getDefinition();
            Category category = categoryFacade.find(Integer.parseInt(categoryParam));
            Classification classification = classificationFacade.find(Integer.parseInt(classificationParam));
            String notion = notionParam;
            String actualIntention = actualIntentionParam;
            String futureIntention = futureIntentionParam;
            String comments = commentsParam;
            
            definition.setCategory(category);
            definition.setClassification(classification);
            definition.setNotion(notion);
            definition.setActualIntention(actualIntention);
            definition.setFutureIntention(futureIntention);
            definition.setComments(comments);
            em.merge(definition);
            em.flush();
            logEvent(user, concept, eventFacade.find(2));
            return concept;
        } catch (Exception ex) {
            context.setRollbackOnly();
            return null;
        }
    }
        
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Concept createConcept(String userParam, String projectParam, String documentParam, String nameParam, String categoryParam, 
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
            Concept concept = addConcept(project, document, name, definition);
            logEvent(user, concept, eventFacade.find(1));
            return concept;
        } catch (Exception ex) {
            context.setRollbackOnly();
            return null;
        }
    }
    
    
}