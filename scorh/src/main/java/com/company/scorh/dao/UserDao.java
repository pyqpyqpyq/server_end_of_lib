package com.company.scorh.dao;

import com.company.scorh.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    @Select("select * from user where username=#{username} and password=#{password}")
    User selectLogin(@Param("username") String username,@Param("password")String password);

    @Select("select * from user where username=#{username}")
    User select(@Param("username") String username);

    @Insert("insert into user (username,password,email,active) values(#{username},#{password},#{email},#{active})")
    int insert( User user);

    @Update("update user set active=#{active} where username=#{username}")
    int updateActive(String username,boolean active);

    @Delete("DELETE FROM user WHERE id=#{userId}")
    int delete(int userId);

    @Update("UPDATE user SET password=#{password} WHERE id=#{id}")
    int update(User user);
}
