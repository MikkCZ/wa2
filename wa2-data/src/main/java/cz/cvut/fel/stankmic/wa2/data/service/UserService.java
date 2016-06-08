package cz.cvut.fel.stankmic.wa2.data.service;

import cz.cvut.fel.stankmic.wa2.data.dao.UserDao;
import cz.cvut.fel.stankmic.wa2.data.entities.User;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.LoginException;
import java.util.List;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User create(String email, String password) {
        User user = new User(email);
        user.setPassword(passwordEncoder.encode(password));
        userDao.create(user);
        return user;
    }

    @Transactional
    public void update(User user, String email, String password) {
        user.setEmail(email);
        if(password != null && !password.isEmpty()) {
            user.setPassword(passwordEncoder.encode(password));
        }
        userDao.update(user);
    }

    @Transactional
    public User findById(int id) {
        return this.userDao.findById(id);
    }

    public User login(String email, String password) throws LoginException {
        User user = this.getByEmail(email);
        if (user == null) {
            throw new LoginException("User does not exist.");
        }
        if (passwordEncoder.matches(password, user.getPassword())) {
            return user;
        } else {
            throw new LoginException("Password does not match.");
        }
    }

    @Transactional
    public User loadWithRatings(int userId) {
        return userDao.getWithRatings(userId);
    }

    public User loadWithRatings(User user) {
        return this.loadWithRatings(user.getId());
    }

    @Transactional
    private User getByEmail(String email) {
        return this.userDao.findByEmail(email);
    }

    @Transactional
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Transactional
    public void delete(int userId) {
        userDao.deleteById(userId);
    }
}
