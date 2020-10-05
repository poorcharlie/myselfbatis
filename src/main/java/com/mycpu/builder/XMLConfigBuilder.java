package com.mycpu.builder;

import com.mycpu.session.Configuration;
import com.mycpu.session.DataSource;
import com.mycpu.utils.ClassUtil;
import com.mycpu.utils.PropertiesUtil;

import java.util.List;

public class XMLConfigBuilder extends BaseBuilder {

    private String properties;
    private PropertiesUtil propertiesUtil;

    public XMLConfigBuilder() {
        this.configuration =  new Configuration();
    }

    public XMLConfigBuilder(String properties) {
        this.properties = properties;
        this.configuration =  new Configuration();
        this.propertiesUtil = new PropertiesUtil(properties);
    }

    public Configuration  parse() {
        //driver=com.mysql.cj.jdbc.Driver
        //url=jdbc:mysql://47.99.193.35:3306/erp?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
        //username=root
        //password=1qaz@WSX

        propertiesDataSource();
        propertiesMappers();

        return this.configuration;
    }

    public void propertiesDataSource() {

        String driver = propertiesUtil.readProperty("driver");
        String url = propertiesUtil.readProperty("url");
        String username = propertiesUtil.readProperty("username");
        String password = propertiesUtil.readProperty("password");

        DataSource dataSource = new DataSource(driver, url, username, password);
        configuration.setDataSource(dataSource);
    }

    public void propertiesMappers() {
        String mappers = propertiesUtil.readProperty("mappers");
        configuration.setMappers(mappers);

        List<Class<?>> classes = ClassUtil.getClasses(mappers);
        for (Class clazz : classes) {
            this.configuration.addMapper(clazz);
        }

    }
}
