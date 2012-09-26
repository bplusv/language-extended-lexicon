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

import java.util.Collection;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Luis Salazar <bp.lusv@gmail.com>
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    @Resource
    protected SessionContext context;
    @EJB
    protected CategoryFacade categoryFacade;
    @EJB
    protected ClassificationFacade classificationFacade;
    @EJB
    protected CommentFacade commentFacade;
    @EJB
    protected DefinitionFacade definitionFacade;
    @EJB
    protected DocumentFacade documentFacade;
    @EJB
    protected EventFacade eventFacade;
    @EJB
    protected LogFacade logFacade;
    @EJB
    protected ProjectFacade projectFacade;
    @EJB
    protected SymbolFacade symbolFacade;
    @EJB
    protected UserFacade userFacade;

    protected abstract EntityManager getEntityManager();

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public T find(String id) {
        try {
            return getEntityManager().find(entityClass, Integer.parseInt(id));
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Collection<T> findAll() {
        try {
            EntityManager em = this.getEntityManager();
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<T> root = cq.from(entityClass);
            cq.select(root);
            return getEntityManager().createQuery(cq).getResultList();
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Collection<T> findRange(int[] range) {
        try {
            CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
            Query q = getEntityManager().createQuery(cq);
            q.setMaxResults(range[1] - range[0]);
            q.setFirstResult(range[0]);
            return q.getResultList();
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Integer count() {
        try {
            CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
            Root<T> rt = cq.from(entityClass);
            cq.select(getEntityManager().getCriteriaBuilder().count(rt));
            Query q = getEntityManager().createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } catch (Exception e) {
            context.setRollbackOnly();
            return null;
        }
    }
}