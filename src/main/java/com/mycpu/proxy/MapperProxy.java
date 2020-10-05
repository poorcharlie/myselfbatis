package com.mycpu.proxy;

import com.mycpu.annotation.mySelect;
import com.mycpu.entity.UserEntiry;
import com.mycpu.session.SqlSession;
import com.mycpu.statement.MappedStatement;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MapperProxy<T> implements InvocationHandler {

    private SqlSession sqlSession;

    private Class<T> mapperInterface;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        mySelect mySelect = method.getAnnotation(mySelect.class);

        if (mySelect == null) {
            throw new Exception("该方法没有定义sql语句，无关联MappedStatement ");
         }

        MappedStatement mappedStatement = new MappedStatement(mySelect, method.getReturnType(), args);

        UserEntiry entiry = sqlSession.selectOne(mappedStatement);

        return entiry;
    }
}
