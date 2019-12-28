package com.company.scorh.dao;

import com.company.scorh.domain.Group;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Repository
public interface GroupDao {
    @Insert("INSERT INTO `group` (user_id,notice_id) VALUES (#{userId},#{noticeId})")
    int insert(Group group);

    @Select("SELECT * FROM `group` WHERE user_id=#{userId} AND notice_id=#{noticeId}")
    Group select(int userId,int noticeId);

    @Delete("DELETE FROM `group` WHERE notice_id=#{noticeId}")
    int delete(int noticeId);

    @Delete("DELETE FROM `group` WHERE user_id=#{userId}")
    int deleteByUserId(int userId);
}
