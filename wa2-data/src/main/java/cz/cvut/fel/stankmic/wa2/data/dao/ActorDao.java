package cz.cvut.fel.stankmic.wa2.data.dao;

import cz.cvut.fel.stankmic.wa2.data.entities.Actor;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

public class ActorDao extends AbstractJpaDao<Actor, Integer> {

    public ActorDao() {
        super(Actor.class);
    }

    private void fetch(Object proxy) {
        Hibernate.initialize(proxy);
    }

    public Actor getWithAll(int id) {
        Actor u = this.findById(id);
        if(u != null) {
            fetch(u.getActorRatings());
            fetch(u.getStagings());
            fetch(u.getTheatres());
        }
        return u;
    }

    public Actor getFullProfile(int actorId) {
        TypedQuery<Actor> q = em.createQuery(
                "select a from actor a" +
                        " left outer join fetch a.stagings s" +
                        " left outer join fetch a.theatres t" +
                        " left outer join fetch a.actorRatings ar" +
                        " left outer join fetch ar.ratedBy" +
                        " where a.id=?1",
                Actor.class
        );
        q.setParameter(1, actorId);
        return q.getSingleResult();
        /*CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Actor> cq = cb.createQuery(Actor.class);
        Root<Actor> r = cq.from(Actor.class);
        cq.where(cb.equal(r.get("id"), actorId));

        r.fetch("stagings", JoinType.LEFT);
        r.fetch("theatres", JoinType.LEFT);
        r.fetch("actorRatings", JoinType.LEFT);

        return em.createQuery(cq).getSingleResult();*/
    }

    public Actor getWithTheatres(int id) {
        Actor u = this.findById(id);
        if(u != null) {
            fetch(u.getTheatres());
        }
        return u;
    }

}
