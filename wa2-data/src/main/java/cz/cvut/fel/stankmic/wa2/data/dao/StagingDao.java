package cz.cvut.fel.stankmic.wa2.data.dao;

import cz.cvut.fel.stankmic.wa2.data.entities.Staging;
import org.hibernate.Hibernate;

public class StagingDao extends AbstractJpaDao<Staging, Integer> {

    public StagingDao() {
        super(Staging.class);
    }

    private void fetch(Object proxy) {
        Hibernate.initialize(proxy);
    }

    public Staging getWithRatings(int id) {
        Staging u = this.findById(id);
        if(u != null) {
            fetch(u.getStagingRatings());
        }
        return u;
    }

}
