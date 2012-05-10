/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.UserActionDef;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lu
 */
@Stateless
public class UserActionDefFacade extends AbstractFacade<UserActionDef> {
    @PersistenceContext(unitName = "lelPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserActionDefFacade() {
        super(UserActionDef.class);
    }
    
}
