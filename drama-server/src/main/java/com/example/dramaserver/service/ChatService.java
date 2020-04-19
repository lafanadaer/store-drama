package com.example.dramaserver.service;

import com.example.dramaserver.domain.Chat;
import com.example.dramaserver.domain.Comment;
import com.example.dramaserver.domain.Drama;

import java.util.List;

public interface ChatService {
    boolean addChat(Chat chat);

    boolean updateChatMsg(Chat chat);

    boolean deleteChat(Integer id);

    List<Chat> allChat();

    List<Chat> chatOfId(Integer id);


    List<Chat> chatOfUserId(Integer userId);

//    List<Chat> commentOfDramaId(Integer dramaId);
//
//    List<Chat> commentOfDramaCateId(Integer dramaCateId);
}
