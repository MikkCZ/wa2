package cz.cvut.fel.stankmic.wa2.data.dao;

import cz.cvut.fel.stankmic.wa2.data.entities.Theatre;
import org.hibernate.Hibernate;

public class TheatreDao extends AbstractJpaDao<Theatre, Integer> {

    public TheatreDao() {
        super(Theatre.class);
    }

    private void fetch(Object proxy) {
        Hibernate.initialize(proxy);
    }

    public Theatre getWithRatings(int id) {
        Theatre u = this.findById(id);
        if(u != null) {
            fetch(u.getTheatreRatings());
        }
        return u;
    }

}
