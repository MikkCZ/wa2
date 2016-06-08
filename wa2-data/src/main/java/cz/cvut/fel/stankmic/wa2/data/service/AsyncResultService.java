package cz.cvut.fel.stankmic.wa2.data.service;

import cz.cvut.fel.stankmic.wa2.data.dao.AsyncResultDao;
import cz.cvut.fel.stankmic.wa2.data.entities.AsyncResult;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class AsyncResultService {

    private final AsyncResultDao asyncResultDao;

    public AsyncResultService(AsyncResultDao asyncResultDao) {
        this.asyncResultDao = asyncResultDao;
    }

    @Transactional
    public AsyncResult create(String result) {
        AsyncResult asyncResult = new AsyncResult(result);
        asyncResultDao.create(asyncResult);
        return asyncResult;
    }

    @Transactional
    public void delete(int resultId) {
        asyncResultDao.deleteById(resultId);
    }

    @Transactional
    public void update(int resultId, String result) {
        AsyncResult asyncResult = asyncResultDao.findById(resultId);
        update(asyncResult, result);
    }

    private void update(AsyncResult asyncResult, String result) {
        asyncResult.setResult(result);
        asyncResultDao.update(asyncResult);
    }

    @Transactional
    public AsyncResult findById(int id) {
        return this.asyncResultDao.findById(id);
    }

}
