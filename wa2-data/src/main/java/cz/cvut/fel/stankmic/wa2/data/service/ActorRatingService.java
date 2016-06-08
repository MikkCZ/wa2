package cz.cvut.fel.stankmic.wa2.data.service;

import cz.cvut.fel.stankmic.wa2.data.dao.ActorDao;
import cz.cvut.fel.stankmic.wa2.data.dao.ActorRatingDao;
import cz.cvut.fel.stankmic.wa2.data.dao.UserDao;
import cz.cvut.fel.stankmic.wa2.data.entities.ActorRating;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class ActorRatingService {

    private final ActorRatingDao actorRatingDao;
    private final UserDao userDao;
    private final ActorDao actorDao;

    public ActorRatingService(ActorRatingDao actorRatingDao, UserDao userDao, ActorDao actorDao) {
        this.actorRatingDao = actorRatingDao;
        this.userDao = userDao;
        this.actorDao = actorDao;
    }

    @Transactional
    public ActorRating create(int userId, int actorId, String comment) {
        ActorRating actorRating = new ActorRating(userDao.findById(userId), actorDao.findById(actorId));
        actorRating.setComment(comment);
        actorRatingDao.create(actorRating);
        return actorRating;
    }

    @Transactional
    public void update(ActorRating actorRating, String comment) {
        actorRating.setComment(comment);
        actorRatingDao.update(actorRating);
    }

    @Transactional
    public ActorRating findById(int id) {
        return this.actorRatingDao.findById(id);
    }
}
