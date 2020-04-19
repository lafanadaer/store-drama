package com.example.dramaserver.dao;

import com.example.dramaserver.domain.Chat;
import com.example.dramaserver.domain.Comment;
import com.example.dramaserver.domain.Drama;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMapper {

    int deleteByPrimaryKey(Integer id);


    int insert(Chat record);


    int insertSelective(Chat record);


    Chat selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(Chat record);


    int updateByPrimaryKey(Chat record);

    int updateChatMsg(Chat record);

    int deleteChat(Integer id);

    List<Chat> allChat();

    List<Chat> chatOfId(Integer id);

    List<Chat> chatOfUserId(Integer userId);

//    List<Chat> commentOfDramaId(Integer dramaId);
//
//    List<Chat> commentOfDramaCateId(Integer dramaCateId);
}
