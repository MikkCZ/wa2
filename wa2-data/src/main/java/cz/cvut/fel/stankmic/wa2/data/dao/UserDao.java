package cz.cvut.fel.stankmic.wa2.data.dao;

import cz.cvut.fel.stankmic.wa2.data.entities.User;
import org.hibernate.Hibernate;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class UserDao extends AbstractJpaDao<User, Integer> {

    public UserDao() {
        super(User.class);
    }

    public User findByEmail(String email) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM users u WHERE email=:email", User.class);
        query.setParameter("email", email);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    private void fetch(Object proxy) {
        Hibernate.initialize(proxy);
    }

    public User getWithRatings(int id) {
        User u = this.findById(id);
        if(u != null) {
            fetch(u.getActorRatings());
            fetch(u.getPlayRatings());
            fetch(u.getTheatreRatings());
        }
        return u;
    }
}
