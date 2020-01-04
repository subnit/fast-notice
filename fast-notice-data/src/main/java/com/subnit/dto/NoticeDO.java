package com.subnit.dto;

import java.util.Date;

public class NoticeDO {
    private Long id;

    private String dbName;

    private String noticeName;

    private Integer noticeInterval;

    private Integer noticeStatus;

    private Date gmtModified;

    private Date gmtCreate;

    private Integer deleteStatus;

    private String sqlText;

    private String noticeExtend;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName == null ? null : dbName.trim();
    }

    public String getNoticeName() {
        return noticeName;
    }

    public void setNoticeName(String noticeName) {
        this.noticeName = noticeName == null ? null : noticeName.trim();
    }

    public Integer getNoticeInterval() {
        return noticeInterval;
    }

    public void setNoticeInterval(Integer noticeInterval) {
        this.noticeInterval = noticeInterval;
    }

    public Integer getNoticeStatus() {
        return noticeStatus;
    }

    public void setNoticeStatus(Integer noticeStatus) {
        this.noticeStatus = noticeStatus;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText == null ? null : sqlText.trim();
    }

    public String getNoticeExtend() {
        return noticeExtend;
    }

    public void setNoticeExtend(String noticeExtend) {
        this.noticeExtend = noticeExtend == null ? null : noticeExtend.trim();
    }

}