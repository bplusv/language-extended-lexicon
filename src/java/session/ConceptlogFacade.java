/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Conceptlog;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lu
 */
@Stateless
public class ConceptlogFacade extends AbstractFacade<Conceptlog> {
    @PersistenceContext(unitName = "lelPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConceptlogFacade() {
        super(Conceptlog.class);
    }
    
}
