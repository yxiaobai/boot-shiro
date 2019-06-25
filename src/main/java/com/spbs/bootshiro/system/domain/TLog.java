package com.spbs.bootshiro.system.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_log")
public class TLog implements Serializable {
    /**
     * 日志ID
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 操作用户
     */
    private String username;

    /**
     * 操作内容
     */
    private String operation;

    /**
     * 耗时
     */
    private Long time;

    /**
     * 操作方法
     */
    private String method;

    /**
     * 方法参数
     */
    private String params;

    /**
     * 操作者IP
     */
    private String ip;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 操作地点
     */
    private String location;

    // 用于搜索条件中的时间字段
    @Transient
    private String timeField;

    /**
     * 获取日志ID
     *
     * @return id - 日志ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置日志ID
     *
     * @param id 日志ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取操作用户
     *
     * @return username - 操作用户
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置操作用户
     *
     * @param username 操作用户
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取操作内容
     *
     * @return operation - 操作内容
     */
    public String getOperation() {
        return operation;
    }

    /**
     * 设置操作内容
     *
     * @param operation 操作内容
     */
    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }

    /**
     * 获取耗时
     *
     * @return time - 耗时
     */
    public Long getTime() {
        return time;
    }

    /**
     * 设置耗时
     *
     * @param time 耗时
     */
    public void setTime(Long time) {
        this.time = time;
    }

    /**
     * 获取操作方法
     *
     * @return method - 操作方法
     */
    public String getMethod() {
        return method;
    }

    /**
     * 设置操作方法
     *
     * @param method 操作方法
     */
    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    /**
     * 获取方法参数
     *
     * @return params - 方法参数
     */
    public String getParams() {
        return params;
    }

    /**
     * 设置方法参数
     *
     * @param params 方法参数
     */
    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    /**
     * 获取操作者IP
     *
     * @return ip - 操作者IP
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置操作者IP
     *
     * @param ip 操作者IP
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取操作地点
     *
     * @return location - 操作地点
     */
    public String getLocation() {
        return location;
    }

    /**
     * 设置操作地点
     *
     * @param location 操作地点
     */
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getTimeField() {
        return timeField;
    }

    public void setTimeField(String timeField) {
        this.timeField = timeField;
    }
}