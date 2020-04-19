package com.example.dramaserver.service.impl;

import com.example.dramaserver.dao.CommentMapper;
import com.example.dramaserver.domain.Comment;
import com.example.dramaserver.service.CommentService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public boolean addComment(Comment comment) {
        return commentMapper.insertSelective(comment) > 0 ? true:false;
    }

    @Override
    public boolean updateCommentMsg(Comment comment) {
        return commentMapper.updateCommentMsg(comment) >0 ?true:false;
    }

    //    删除评论
    @Override
    public boolean deleteComment(Integer id) {
        return commentMapper.deleteComment(id) >0 ?true:false;
    }

    @Override
    public List<Comment> allComment()
    {
        return commentMapper.allComment();
    }

    @Override
    public List<Comment> commentOfDramaId(Integer dramaId)

    {
        return commentMapper.commentOfDramaId(dramaId);
    }

    @Override
    public List<Comment> commentOfChatId(Integer chatId)

    {
        return commentMapper.commentOfChatId(chatId);
    }

    @Override
    public List<Comment> commentOfUserId(Integer userId)

    {
        return commentMapper.commentOfUserId(userId);
    }
}
