package com.mycpu.executor;

import com.mycpu.statement.MappedStatement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseExecutor {

    private Map<Object, Object> cache = new HashMap<>();

    public <E> List<E> query(MappedStatement ms) {
        List<E> list = (List<E>) cache.get(ms.getMappedStatementKey());

        if (list != null) {
            return list;
        }

        return queryFromDatabase(ms);
    }

    private <E> List<E> queryFromDatabase(MappedStatement ms) {

        List<E> list = doQuery(ms);

        if (list != null) {
            cache.put(ms.getMappedStatementKey(), list);
        }


        return list;
    }

    protected abstract <E> List<E> doQuery(MappedStatement ms) ;
}
