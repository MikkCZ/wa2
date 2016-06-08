package cz.cvut.fel.stankmic.wa2.data.dao;

import cz.cvut.fel.stankmic.wa2.data.entities.AsyncResult;

public class AsyncResultDao extends AbstractJpaDao<AsyncResult, Integer> {

    public AsyncResultDao() {
        super(AsyncResult.class);
    }

}
