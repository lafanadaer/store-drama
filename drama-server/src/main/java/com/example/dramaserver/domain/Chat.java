package com.example.dramaserver.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class Chat {

    private Integer id;

    private Integer userId;

    private String title;

    private String content;

    private Date createTime;

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

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }



    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }


    public Date getCreateTime() {
        return createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }




    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}