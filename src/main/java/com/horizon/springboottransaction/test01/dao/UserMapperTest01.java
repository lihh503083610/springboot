package com.horizon.springboottransaction.test01.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * @author lihh 17:27
 */
public interface UserMapperTest01 {

    @Insert("INSERT INTO TEST01(NAME,AGE) VALUES(${name}, ${age})")
    int insert(@Param("name") String name, @Param("age") int age);
}
