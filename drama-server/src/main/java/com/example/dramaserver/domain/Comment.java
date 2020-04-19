package com.example.dramaserver.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class Comment {

    private Integer id;


    private Integer userId;


    private Integer dramaId;


    private Integer chatId;


    private String content;


    private Date createTime;


    private Byte type;

    private Integer up;


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


    public Integer getDramaId() {
        return dramaId;
    }


    public void setDramaId(Integer dramaId) {
        this.dramaId = dramaId;
    }


    public Integer getChatId() {
        return chatId;
    }


    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

//    public Integer getUserId() {
//        return userId;
//    }
//
//
//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }


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

    public Byte getType() {
        return type;
    }


    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getUp() {
        return up;
    }

    public void setUp(Integer up) {
        this.up = up;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}