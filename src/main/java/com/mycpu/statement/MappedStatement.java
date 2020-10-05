package com.mycpu.statement;

import com.mycpu.annotation.mySelect;
import com.mycpu.session.Configuration;
import lombok.Data;

import java.lang.annotation.Annotation;

@Data
public class MappedStatement {

    private Annotation sqlType;

    private String sqlValue;

    private Class<?> returnType;

    private Object[] args;

    private Configuration configuration;

    public MappedStatement(Annotation sqlType,  Class<?> returnType, Object[] args) {


        this.sqlType = sqlType;
        this.returnType = returnType;
        this.args = args;
        getinstanceSqlType();
    }

    private void getinstanceSqlType() {
        if (sqlType instanceof mySelect) {
            mySelect mySelect = (mySelect) sqlType;
            sqlValue = mySelect.value();
        }
    }


    public String getMappedStatementKey() {
        String key = sqlValue
                + arrayToString()
                + returnType;
//        System.out.println("key : " + key);
        return key;
    }

    private String arrayToString() {
        StringBuilder sb = new StringBuilder();
        for (Object o : args) {
            sb.append(o);
        }

        return sb.toString();
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
