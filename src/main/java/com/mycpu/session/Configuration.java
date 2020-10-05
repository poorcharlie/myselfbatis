package com.mycpu.session;

import com.mycpu.proxy.MapperProxyFactory;

import java.util.HashMap;
import java.util.Map;


public class Configuration {

    private DataSource dataSource;

    private String mappers;

    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers =
            new HashMap<Class<?>, MapperProxyFactory<?>>();


    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }



    public void setMappers(String mappers) {
        this.mappers = mappers;
    }

    public <T> void addMapper(Class<T> type) {
        knownMappers.put(type, new MapperProxyFactory<>(type));
    }

    public <T> T getMapper(Class<T> type) {
        return (T) knownMappers.get(type);
    }
}
