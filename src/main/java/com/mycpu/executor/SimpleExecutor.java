package com.mycpu.executor;

import com.mycpu.session.Configuration;
import com.mycpu.statement.MappedStatement;
import com.mycpu.utils.JDBCutil;

import java.util.List;

public class SimpleExecutor extends BaseExecutor {


    @Override
    protected <E> List<E> doQuery(MappedStatement ms) {

        System.out.println("开始执行数据库 ms:" + ms.toString());
        Configuration configuration = ms.getConfiguration();
        JDBCutil jdbCutil = new JDBCutil(configuration.getDataSource());
        List<E> classes = (List<E>) jdbCutil.executeQuery(ms.getSqlValue(), ms.getReturnType(), ms.getArgs());

        return classes;
    }
}
