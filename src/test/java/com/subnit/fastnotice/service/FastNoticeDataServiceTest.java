package com.subnit.fastnotice.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * description:
 * date : create in 下午10:39 2019/12/23
 * modified by :
 *
 * @author subo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FastNoticeDataServiceTest {


    @Autowired
    private FastNoticeDataService fastNoticeDataService;

    @Test
    public void addNotice() throws InterruptedException {
        String db = "dataSource";
        String sql = "select count(*) from notice";
        String name = "测试";
        Integer interval = 1000;
        String email = "subnit@163.com";
        String dingWebHook = "{\"webHook\":\"https://oapi.dingtalk.com/robot/send?access_token=489f1600814a3175b5b4d048de8ac08f3ad3156a8242a7ee31268f9542f6dfb2\",\"atMobiles\":\"13241901419\"}";
        fastNoticeDataService.addNotice( db,  sql,  name,  interval,  email,  dingWebHook);
        Thread.sleep(100000000);
    }
}