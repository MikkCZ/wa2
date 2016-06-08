package cz.cvut.fel.stankmic.wa2.data.service;

import cz.cvut.fel.stankmic.wa2.data.dao.TheatreDao;
import cz.cvut.fel.stankmic.wa2.data.entities.Theatre;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class TheatreService {

    private final TheatreDao theatreDao;

    public TheatreService(TheatreDao theatreDao) {
        this.theatreDao = theatreDao;
    }

    @Transactional
    public Theatre create(String name) {
        Theatre theatre = new Theatre(name);
        theatreDao.create(theatre);
        return theatre;
    }

    @Transactional
    public void update(Theatre theatre, String name) {
        theatre.setName(name);
        theatreDao.update(theatre);
    }

    @Transactional
    public Theatre findById(int id) {
        return this.theatreDao.findById(id);
    }

    @Transactional
    public Theatre loadWithRatings(int theatreId) {
        return theatreDao.getWithRatings(theatreId);
    }

    public Theatre loadWithRatings(Theatre theatre) {
        return this.loadWithRatings(theatre.getId());
    }

    @Transactional
    public List<Theatre> getAll() {
        return theatreDao.getAll();
    }

    @Transactional
    public void delete(int theatreId) {
        theatreDao.deleteById(theatreId);
    }
}
