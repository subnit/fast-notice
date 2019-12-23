package com.subnit.fastnotice.dao;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * description:
 * date : create in 下午10:26 2019/12/23
 * modified by :
 *
 * @author subo
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