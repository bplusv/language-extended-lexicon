/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.ConceptActionDef;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lu
 */
@Stateless
public class ConceptActionDefFacade extends AbstractFacade<ConceptActionDef> {
    @PersistenceContext(unitName = "lelPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConceptActionDefFacade() {
        super(ConceptActionDef.class);
    }
    
}
