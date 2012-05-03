/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.Userlog;

/**
 *
 * @author YANET
 */
@Stateless
public class UserlogFacade extends AbstractFacade<Userlog> {
    @PersistenceContext(unitName = "lelPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserlogFacade() {
        super(Userlog.class);
    }
    
}
