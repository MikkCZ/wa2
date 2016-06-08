package cz.cvut.fel.stankmic.wa2.data.dao;

import cz.cvut.fel.stankmic.wa2.data.entities.ActorRating;

public class ActorRatingDao extends AbstractJpaDao<ActorRating, Integer> {

    public ActorRatingDao() {
        super(ActorRating.class);
    }

}
