package fastnotice.subnit.controller;


import fastnotice.subnit.service.FastNoticeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description:
 * date : create in 下午11:39 2019/12/28
 * modified by :
 *
 * @author subo
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private FastNoticeDataService fastNoticeDataService;


    @RequestMapping("startNotice")
    public Boolean startNotice(Long noticeId) {
        fastNoticeDataService.startNotice(noticeId);
        return true;
    }

    @RequestMapping("stopNotice")
    public Boolean stopNotice(Long noticeId) {
        fastNoticeDataService.stopNotice(noticeId);
        return true;
    }

}
