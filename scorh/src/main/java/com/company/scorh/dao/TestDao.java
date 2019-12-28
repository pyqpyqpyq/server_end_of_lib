package com.company.scorh.dao;

import com.company.scorh.domain.Test;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface TestDao {
    @Insert("insert into `test` (`date`,`datetime`) values (#{date},#{datetime})")
    int insert(Test test);

    @Select("select * from `test` where id=#{id}")
    Test select(int id);
}
