/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Concept;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lu
 */
@Stateless
public class ConceptFacade extends AbstractFacade<Concept> {
    @PersistenceContext(unitName = "lelPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConceptFacade() {
        super(Concept.class);
    }
    
}
