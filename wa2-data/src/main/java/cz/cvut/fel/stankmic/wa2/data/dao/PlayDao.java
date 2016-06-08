package cz.cvut.fel.stankmic.wa2.data.dao;

import cz.cvut.fel.stankmic.wa2.data.entities.Play;
import org.hibernate.Hibernate;

public class PlayDao extends AbstractJpaDao<Play, Integer> {

    public PlayDao() {
        super(Play.class);
    }

    private void fetch(Object proxy) {
        Hibernate.initialize(proxy);
    }

    public Play getWithRatings(int id) {
        Play u = this.findById(id);
        if(u != null) {
            fetch(u.getPlayRatings());
        }
        return u;
    }

}
