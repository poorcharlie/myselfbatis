package com.mycpu.session;

import com.mycpu.executor.BaseExecutor;
import com.mycpu.proxy.MapperProxyFactory;
import com.mycpu.statement.MappedStatement;

import java.util.List;

public class DefaultSqlSession implements SqlSession {

    private final Configuration configuration;

    private BaseExecutor baseExecutor;

    public DefaultSqlSession(Configuration configuration, BaseExecutor baseExecutor) {
        this.configuration = configuration;
        this.baseExecutor = baseExecutor;
    }

    @Override
    public <T> T getMapper(Class<T> type) throws Exception {
        MapperProxyFactory mapperProxyFactory = (MapperProxyFactory)
                configuration.getMapper(type);

        if (mapperProxyFactory == null) {
            throw new Exception("error,type :" + type + "is no known");
        }

        return (T) mapperProxyFactory.newInstance(this);
    }

    @Override
    public <T> T selectOne(MappedStatement statement) throws Exception {
        List<T> list = this.selectList(statement);
        if (list.size() == 1) {
            return list.get(0);
        } else if (list.size() > 1) {
            throw new Exception("Expected one result (or null) to be returned by selectOne(), but found: " + list.size());
        } else {
            return null;
        }
    }

    @Override
    public <E> List<E> selectList(MappedStatement ms) {
        ms.setConfiguration(configuration);
        return baseExecutor.query(ms);
    }
}
