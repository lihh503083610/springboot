package com.horizon.springboottransaction.test01.service;

import com.horizon.springboottransaction.test01.dao.UserMapperTest01;
import com.horizon.springboottransaction.test02.dao.UserMapperTest02;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lihh 17:55
 */
@Service
public class UserServiceTest01 {

    @Autowired
    private UserMapperTest01 userMapperTest01;

    @Autowired
    private UserMapperTest02 userMapperTest02;

    @Transactional
    public int insertData(String name, int age){

        userMapperTest01.insert(name, age);
        userMapperTest02.insert(name, age);
        int a = 1/0;
        return 1;
    }


}
