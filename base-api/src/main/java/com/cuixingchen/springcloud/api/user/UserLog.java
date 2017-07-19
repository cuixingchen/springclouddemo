package com.cuixingchen.springcloud.api.user;

import java.util.Date;

/**
 * Created by cuipengfei on 17-7-19.
 */
public class UserLog {

    private Long id;

    private String text;

    private Date createTime;

    private Date updateTime;

    private Long userId;

    public UserLog() {
    }

    public UserLog(String text, Long userId) {
        this.text = text;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
