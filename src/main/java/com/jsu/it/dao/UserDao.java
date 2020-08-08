package com.jsu.it.dao;

import com.jsu.it.entry.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    @Select("select * from user where id=#{id}")
     User queryUserById(int id);

    @Insert("insert into user(username,password,email,isroot) values(#{username},#{password},#{email},#{isroot})")
//    @Insert("insert into user(username) values(#{username})")
    @Options(useGeneratedKeys=true, keyProperty="uid", keyColumn="uid")
     void addUser(User user);

    @Select("select * from user where username = #{username}")
    User hasUserName(String username);

    @Select("select * from user where username = #{username} and password = #{password}")
    User hasUser(User user);
}
