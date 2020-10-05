package com.mycpu.session;

import com.mycpu.statement.MappedStatement;

import java.util.List;

public interface SqlSession {

    <T> T getMapper(Class<T> type) throws Exception;

    <T> T selectOne(MappedStatement ms) throws Exception;

    <E> List<E> selectList(MappedStatement ms);


}
