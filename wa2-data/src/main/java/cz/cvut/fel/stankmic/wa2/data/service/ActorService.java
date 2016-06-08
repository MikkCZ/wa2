package cz.cvut.fel.stankmic.wa2.data.service;

import cz.cvut.fel.stankmic.wa2.data.dao.ActorDao;
import cz.cvut.fel.stankmic.wa2.data.dao.TheatreDao;
import cz.cvut.fel.stankmic.wa2.data.entities.Actor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class ActorService {

    private final ActorDao actorDao;
    private final TheatreDao theatreDao;

    public ActorService(ActorDao actorDao, TheatreDao theatreDao) {
        this.actorDao = actorDao;
        this.theatreDao = theatreDao;
    }

    @Transactional
    public Actor create(String firstName, String lastName) {
        Actor actor = new Actor(firstName, lastName);
        actorDao.create(actor);
        return actor;
    }

    @Transactional
    public void delete(int actorId) {
        actorDao.deleteById(actorId);
    }

    @Transactional
    public void update(Actor actor, String firstName, String lastName) {
        actor.setFirstName(firstName);
        actor.setLastName(lastName);
        actorDao.update(actor);
    }

    @Transactional
    public void addTheatre(Actor actor, int theatreId) {
        actor.getTheatres().add(theatreDao.findById(theatreId));
        actorDao.update(actor);
    }

    @Transactional
    public void removeTheatre(Actor actor, int theatreId) {
        actor.getTheatres().remove(theatreDao.findById(theatreId));
        actorDao.update(actor);
    }

    @Transactional
    public Actor findById(int id) {
        return this.actorDao.findById(id);
    }

    @Transactional
    public Actor loadWithAll(int actorId) {
        return actorDao.getWithAll(actorId);
    }

    public Actor loadWithAll(Actor actor) {
        return this.loadWithAll(actor.getId());
    }

    @Transactional
    public Actor loadWithTheatres(int actorId) {
        return actorDao.getWithTheatres(actorId);
    }

    public Actor loadWithTheatres(Actor actor) {
        return this.loadWithTheatres(actor.getId());
    }

    @Transactional
    public List<Actor> getAll() {
        return actorDao.getAll();
    }
}
