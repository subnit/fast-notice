package com.subnit.fastnotice.service;

import com.subnit.fastnotice.dao.NoticeDao;
import com.subnit.fastnotice.dao.NoticeMethodDao;
import com.subnit.fastnotice.dto.NoticeDO;
import com.subnit.fastnotice.dto.NoticeMethodDO;
import com.subnit.fastnotice.service.notice.method.NoticeMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    public static ConcurrentHashMap<Long, Boolean> noticeStatusMap = new ConcurrentHashMap<>();

    @Autowired
    private NoticeDao noticeDao;

    @Autowired
    private NoticeMethodDao noticeMethodDao;

    /**
     *
     * @param db name of db
     * @param sql only query sql are allowed
     * @param name notice name
     * @param interval  ms
     */
    public boolean addNotice(String db, String sql, String name, Integer interval) {
        NoticeDO noticeForInsert = new NoticeDO();
        noticeForInsert.setDbName(db);
        noticeForInsert.setSqlText(sql);
        noticeForInsert.setNoticeName(name);
        noticeForInsert.setNoticeInterval(interval);
        noticeForInsert.setNoticeStatus(0);
        noticeForInsert.setDeleteStatus(1);
        Date nowDate = new Date();
        noticeForInsert.setGmtModified(nowDate);
        noticeForInsert.setGmtCreate(nowDate);
        noticeDao.insert(noticeForInsert);
        return true;
    }


    public void addNoticeMethod(NoticeMethodDO noticeMethodDO) {
        noticeMethodDao.insert(noticeMethodDO);
    }

    public void startNotice(Long noticeId) {
        NoticeDO noticeDO = noticeDao.selectByPrimaryKey(noticeId);
        NoticeDO noticeForUpdate = new NoticeDO();
        noticeForUpdate.setId(noticeDO.getId());
        noticeForUpdate.setNoticeStatus(1);
        noticeDao.updateByPrimaryKey(noticeForUpdate);

        String noticeName = noticeDO.getNoticeName();
        Integer noticeInterval = noticeDO.getNoticeInterval();
        List<NoticeMethodDO> noticeMethodList = noticeMethodDao.listByNoticeId(noticeId);
        ExecutorService threadPool = Executors.newCachedThreadPool();
        noticeStatusMap.put(noticeId, true);
        threadPool.execute(() -> {
            Boolean noticeStatus = FastNoticeDataService.noticeStatusMap.get(noticeId);
            while (noticeStatus != null && noticeStatus) {
                try {
                    DataSource dataSource = getDataSource(noticeDO.getDbName());
                    dataSource.getConnection();
                    Statement s = dataSource.getConnection().createStatement();
                    ResultSet rs = s.executeQuery(noticeDO.getSqlText());

                    int dataCount = 0;
                    while(rs.next()) {
                        dataCount++;
                    }
                    if (dataCount > 0) {
                        String title = String.format("通知提醒:%s", noticeName);
                        String content = String.format("%s有%d条异常数据", noticeName, dataCount);
                        Map<String, NoticeMethod> beansOfType = applicationContext.getBeansOfType(NoticeMethod.class);

                        noticeMethodList.forEach(dto -> {
                            String noticeMethodService = dto.getNoticeMethodService();
                            NoticeMethod noticeMethod = beansOfType.get(noticeMethodService);
                            if (noticeMethod != null) {
                                noticeMethod.sendNotice(title, content, dto.getTarget());
                            }
                        });

                    }
                    Thread.sleep(noticeInterval * 1000);
                } catch (InterruptedException | SQLException e) {
                    e.printStackTrace();
                }
            }

        });
    }



    public void stopNotice(Long noticeId) {
        noticeStatusMap.put(noticeId, false);
        NoticeDO noticeForUpdate = new NoticeDO();
        noticeForUpdate.setId(noticeId);
        noticeForUpdate.setNoticeStatus(1);
        noticeDao.updateByPrimaryKey(noticeForUpdate);
    }

    private DataSource getDataSource(String db) {
        Map<String, DataSource> dataSourceMap = applicationContext.getBeansOfType(DataSource.class);
        return dataSourceMap.get(db);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
