package com.company.scorh;

import com.company.scorh.dao.TestDao;
import com.company.scorh.dao.UserDao;
import com.company.scorh.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class ScorhApplicationTests {

    @Autowired
    UserDao userDao;
    @Autowired
    TestDao testDao;

    @Test
    void contextLoads() throws ParseException {

    }

    @Test
    void insertTest() throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        com.company.scorh.domain.Test test=new com.company.scorh.domain.Test();
        test.setId(2);
        Date logDate=sdf.parse("1900-01-02");
        test.setDate(logDate);
        sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date logDatetime=sdf.parse("1900-01-01 11:11:11");
        test.setDatetime(logDatetime);
        testDao.insert(test);
    }

    @Test
    void selectTest() throws ParseException {
        com.company.scorh.domain.Test test=testDao.select(2);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=sdf.parse("1900-01-02");
        System.out.println(date.equals(test.getDate()));
    }

}
