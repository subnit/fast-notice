package com.subnit.notice.data.service;

import com.subnit.notice.data.dto.NoticeDO;
import com.subnit.notice.data.dto.NoticeMethodDO;

import java.util.List;

/**
 * description:
 * date : create in 上午8:59 2020/3/6
 * modified by :
 *
 * @author subo
 */
public class NoticeSecheduledExecutor implements Runnable{

    private NoticeDO noticeDO;

    private List<NoticeMethodDO> noticeMethodList;
    private FastNoticeDataService fastNoticeDataService;


    public NoticeSecheduledExecutor(NoticeDO noticeDO,  List<NoticeMethodDO> noticeMethodList, FastNoticeDataService fastNoticeDataService) {
        super();
        this.noticeDO = noticeDO;
        this.noticeMethodList = noticeMethodList;
        this.fastNoticeDataService = fastNoticeDataService;
    }



    @Override
    public void run() {
        fastNoticeDataService.noticeExecute(noticeDO, noticeMethodList);
    }
}
