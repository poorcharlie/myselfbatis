package com.mycpu.mappers;

import com.mycpu.annotation.mySelect;
import com.mycpu.entity.UserEntiry;

public interface UserMapper {

    @mySelect("select * from user where userid = ?")
    UserEntiry selectUser(Long id);
}
