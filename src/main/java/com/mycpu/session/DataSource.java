package com.mycpu.session;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DataSource {

    //driver=com.mysql.cj.jdbc.Driver
    //url=jdbc:mysql://47.99.193.35:3306/erp?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
    //username=root
    //password
    private String driver;
    private String url;
    private String username;
    private String password;
}
