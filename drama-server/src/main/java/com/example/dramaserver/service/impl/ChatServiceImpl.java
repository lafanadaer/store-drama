package com.example.dramaserver.service.impl;

import com.example.dramaserver.dao.ChatMapper;

import com.example.dramaserver.domain.Chat;

import com.example.dramaserver.domain.Drama;
import com.example.dramaserver.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatMapper chatMapper;

    @Override
    public boolean addChat(Chat chat) {
        return chatMapper.insertSelective(chat) > 0 ? true:false;
    }

    @Override
    public boolean updateChatMsg(Chat chat) {
        return chatMapper.updateChatMsg(chat) >0 ?true:false;
    }

    //    删除帖子
    @Override
    public boolean deleteChat(Integer id) {
        return chatMapper.deleteChat(id) >0 ?true:false;
    }

    @Override
    public List<Chat> allChat()
    {
        return chatMapper.allChat();
    }

    @Override
    public List<Chat> chatOfId(Integer id)

    {
        return chatMapper.chatOfId(id);
    }

    @Override
    public List<Chat> chatOfUserId(Integer userId)

    {
        return chatMapper.chatOfUserId(userId);
    }


}
