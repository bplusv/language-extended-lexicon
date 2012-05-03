/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.Useractiondef;

/**
 *
 * @author YANET
 */
@Stateless
public class UseractiondefFacade extends AbstractFacade<Useractiondef> {
    @PersistenceContext(unitName = "lelPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UseractiondefFacade() {
        super(Useractiondef.class);
    }
    
}
