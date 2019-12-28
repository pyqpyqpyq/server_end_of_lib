package com.company.scorh.domain;

public class Group {
    private Integer id;
    private Integer userId;
    private Integer noticeId;

    public Group(Integer userId, Integer noticeId) {
        this.userId = userId;
        this.noticeId = noticeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }
}
