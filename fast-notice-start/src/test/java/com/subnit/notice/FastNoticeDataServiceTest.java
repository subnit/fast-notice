package com.subnit.notice;

import com.alibaba.fastjson.JSONObject;
import com.subnit.notice.data.dao.NoticeDao;
import com.subnit.notice.data.dto.NoticeMethodDO;
import com.subnit.notice.data.service.FastNoticeDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

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

    @Autowired
    private NoticeDao noticeDao;

    @Test
    public void addNotice() {
        String db = "dataSource";
        String sql = "select count(*) from notice";
        String name = "测试";
        Integer interval = 1000;
        fastNoticeDataService.addNotice( db,  sql,  name,  interval);
    }

    @Test
    public void addNoticeMethod() {
/*        NoticeMethodDO noticeMethodDO = new NoticeMethodDO();
        noticeMethodDO.setNoticeId(7L);
        noticeMethodDO.setNoticeMethod("email");
        noticeMethodDO.setNoticeMethodService("emailNoticeMethod");
        noticeMethodDO.setTarget("subnit@163.com");
        Date nowDate = new Date();
        noticeMethodDO.setGmtModified(nowDate);
        noticeMethodDO.setGmtCreate(nowDate);*/

        NoticeMethodDO noticeMethodDO = new NoticeMethodDO();
        noticeMethodDO.setNoticeId(7L);
        noticeMethodDO.setNoticeMethod("ding");
        noticeMethodDO.setNoticeMethodService("dingNoticeMethod");
        String dingWebHook = "{\"webHook\":\"https://oapi.dingtalk.com/robot/send?access_token=489f1600814a3175b5b4d048de8ac08f3ad3156a8242a7ee31268f9542f6dfb2\",\"atMobiles\":\"13241901419\"}";
        noticeMethodDO.setTarget(dingWebHook);
        Date nowDate = new Date();
        noticeMethodDO.setGmtModified(nowDate);
        noticeMethodDO.setGmtCreate(nowDate);
        fastNoticeDataService.addNoticeMethod(noticeMethodDO);
    }

    @Test
    public void startNotice() throws InterruptedException {
        System.out.println(JSONObject.toJSONString(noticeDao.selectAll()));





        fastNoticeDataService.startNotice(7L);
        Thread.sleep(100000000);
    }

    @Test
    public void stopNotice() {
        fastNoticeDataService.stopNotice(7L);
    }
}