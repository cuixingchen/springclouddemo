package com.cuixingchen.springcloud.api.user;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by cuipengfei on 17-6-20.
 */
public class UserPojo implements Serializable {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 用户编码
     */
    private String userId;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 描述信息
     */
    private String descript;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 有效状态，0：无效，1：有效
     */
    private Byte state;
    /**
     * 物理删除，0：删除，1：存在
     */
    private Byte status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
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

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
