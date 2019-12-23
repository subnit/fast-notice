package com.subnit.fastnotice.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.MessagingException;
import javax.sql.DataSource;
import com.subnit.fastnotice.util.NoticeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * description:
 * date : create in 下午10:26 2019/12/23
 * modified by :
 *
 * @author subo
 */
@Service
public class FastNoticeDataService implements ApplicationContextAware {

    Logger logger = LoggerFactory.getLogger(getClass());


    private ApplicationContext applicationContext;

    public static ConcurrentHashMap<Integer, Boolean> noticeStatusMap = new ConcurrentHashMap<>();

    /**
     *
     * @param db name of db
     * @param sql only query sql are allowed
     * @param name notice name
     * @param interval  ms
     * @param email notice email
     * @param dingWebHook dingding WebHook for noitce, you can get the WebHook in a dingding chat group
     */
    public boolean addNotice(String db, String sql, String name, Integer interval, String email, String dingWebHook) {
        // 1 get DataSource
        DataSource dataSource = getDataSource(db);

        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        // TODO插入一条记录 获取主键id
        Integer noticeId = 0;
        noticeStatusMap.put(noticeId, true);
        threadPool.execute(() -> {
            while (FastNoticeDataService.noticeStatusMap.get(noticeId)) {
                try {
                    dataSource.getConnection();
                    Statement s = dataSource.getConnection().createStatement();
                    ResultSet rs = s.executeQuery(sql);

                    int dataCount = 0;
                    while(rs.next()) {
                        dataCount++;
                    }
                    if (dataCount > 0) {
                        String title = String.format("通知提醒:%s", name);
                        String content = String.format("%s有%d条异常数据", name, dataCount);
                        NoticeUtils.sendEmail(title, content, email);
                        NoticeUtils.sendDingMessage(dingWebHook, title + "\n" + content, "");

                    }
                    Thread.sleep(interval);
                } catch (InterruptedException | SQLException | MessagingException e) {
                    e.printStackTrace();
                }
            }

        });

        return true;
    }

    public void updateNoticeStatus(Integer noticeId, Boolean status) {
        noticeStatusMap.put(noticeId, status);
        // TODO 更新记录状态
    }

    private DataSource getDataSource(String db) {
        Map<String, DataSource> dataSourceMap = applicationContext.getBeansOfType(DataSource.class);
        return dataSourceMap.get("dataSource");
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}