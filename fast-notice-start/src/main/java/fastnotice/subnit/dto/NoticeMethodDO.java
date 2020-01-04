package fastnotice.subnit.dto;

import java.util.Date;

public class NoticeMethodDO {
    private Long id;

    private Long noticeId;

    private String noticeMethod;

    private String noticeMethodService;

    private Date gmtModified;

    private Date gmtCreate;

    private String target;

    private String extend;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeMethod() {
        return noticeMethod;
    }

    public void setNoticeMethod(String noticeMethod) {
        this.noticeMethod = noticeMethod == null ? null : noticeMethod.trim();
    }

    public String getNoticeMethodService() {
        return noticeMethodService;
    }

    public void setNoticeMethodService(String noticeMethodService) {
        this.noticeMethodService = noticeMethodService == null ? null : noticeMethodService.trim();
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

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend == null ? null : extend.trim();
    }
}