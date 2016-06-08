package cz.cvut.fel.stankmic.wa2.data.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

@Repository
public abstract class AbstractJpaDao<T extends Serializable, ID extends Object> {

    @PersistenceContext
    protected EntityManager em;

    private final Class<T> cls;

    protected AbstractJpaDao(Class<T> cls) {
        this.cls = cls;
    }

    public void create(T entity) {
        em.persist(entity);
    }

    public T findById(ID id) {
        return em.find(this.cls, id);
    }

    public T update(T entity){
        return em.merge(entity);
    }

    public void delete(T entity){
        em.remove(entity);
    }

    public void deleteById(ID id) {
        T entity = findById(id);
        delete(entity);
    }

    public List<T> getAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(cls);
        Root<T> r = cq.from(cls);
        cq.select(r);
        cq.orderBy(cb.desc(r.get("id")).reverse());
        return em.createQuery(cq).getResultList();
    }

}
