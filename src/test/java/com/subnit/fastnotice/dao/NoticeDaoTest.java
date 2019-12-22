package com.subnit.fastnotice.dao;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author sb177693
 * @date 2019/12/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class NoticeDaoTest {


    @Autowired
    NoticeDao noticeDao;

    @Test
    void findByName() {
        System.out.println(noticeDao.findByName());
    }
}