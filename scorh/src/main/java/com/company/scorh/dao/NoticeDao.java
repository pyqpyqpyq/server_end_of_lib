package com.company.scorh.dao;

import com.company.scorh.domain.Notice;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeDao {
    @Select("select * from notice where reserved=0")
    List<Notice> selectAll();

    @Select("SELECT * FROM notice WHERE id IN (SELECT notice_id FROM `group` WHERE user_id=#{userId})")
    List<Notice> selectByUserId(int userId);

    @Select("SELECT reserved FROM notice WHERE id=#{id}")
    boolean selectReserved(int id);

    @Update("update notice set reserved=#{reserved} where id=#{id}")
    int updateReserved(int id,boolean reserved);

    @Update("update notice set reserved=#{reserved} where user_id=#{userId}")
    int updateReservedByUserId(int userId,boolean reserved);
}
