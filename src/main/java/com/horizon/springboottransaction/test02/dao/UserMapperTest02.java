package com.horizon.springboottransaction.test02.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * @author lihh 17:27
 */
public interface UserMapperTest02 {

    @Insert("INSERT INTO TEST02(NAME,AGE) VALUES(${name}, ${age})")
    int insert(@Param("name") String name, @Param("age") int age);
}
