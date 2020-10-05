package com.mycpu.session;

import com.mycpu.builder.XMLConfigBuilder;

public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(String properties) {
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(properties);

        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration config) {
        return new DefaultSqlSessionFactory(config);
    }
}
