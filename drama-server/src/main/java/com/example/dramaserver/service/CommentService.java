package com.example.dramaserver.service;

import com.example.dramaserver.domain.Comment;

import java.util.List;
public interface CommentService {

    boolean addComment(Comment comment);

    boolean updateCommentMsg(Comment comment);

    boolean deleteComment(Integer id);

    List<Comment> allComment();

    List<Comment> commentOfDramaId(Integer dramaId);

    List<Comment> commentOfChatId(Integer chatId);

    List<Comment> commentOfUserId(Integer userId);

}
