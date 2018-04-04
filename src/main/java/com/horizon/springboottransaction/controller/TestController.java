package com.horizon.springboottransaction.controller;

import com.horizon.springboottransaction.test01.service.UserServiceTest01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lihh 19:32
 */
@RestController
public class TestController {

    @Autowired
    UserServiceTest01 userServiceTest01;

    @RequestMapping("/insert")
    public String insertData(String name, int age){

        userServiceTest01.insertData(name, age);
        return "success";
    }

}
