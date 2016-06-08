package cz.cvut.fel.stankmic.wa2.data.dao;

import cz.cvut.fel.stankmic.wa2.data.entities.Actor;
import org.hibernate.Hibernate;

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

    public Actor getWithTheatres(int id) {
        Actor u = this.findById(id);
        if(u != null) {
            fetch(u.getTheatres());
        }
        return u;
    }

}
