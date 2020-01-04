package com.subnit.notice.data.service.notice.method;

/**
 * description:
 * date : create in 下午11:16 2019/12/25
 * modified by :
 *
 * @author subo
 */
public  interface NoticeMethod {

    Boolean sendNotice(String title, String content, String target);
}
