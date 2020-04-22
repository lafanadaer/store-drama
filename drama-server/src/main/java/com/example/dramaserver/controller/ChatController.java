package com.example.dramaserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.dramaserver.domain.Chat;

import com.example.dramaserver.service.impl.ChatServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@Controller
public class ChatController {

    @Autowired
    private ChatServiceImpl chatService;

    //  发布帖子
    @ResponseBody
    @RequestMapping(value = "/chat/add", method = RequestMethod.POST)
    public Object addChat(HttpServletRequest req){

        JSONObject jsonObject = new JSONObject();
        String user_id = req.getParameter("userId");
        String title = req.getParameter("title").trim();
        String content = req.getParameter("content").trim();

        Chat chat = new Chat();
        chat.setUserId(Integer.parseInt(user_id));
        chat.setTitle(title);
        chat.setContent(content);
        chat.setCreateTime(new Date());
        boolean res = chatService.addChat(chat);
        if (res){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "评论成功");
            return jsonObject;
        }else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "评论失败");
            return jsonObject;
        }
    }

    @RequestMapping(value = "/chat/detail", method = RequestMethod.GET)
    public Object dramaOfId(HttpServletRequest req){
        String id = req.getParameter("id");
        return chatService.chatOfId(Integer.parseInt(id));
    }

    //    获取所有帖子列表
    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public Object allChat(){
        return chatService.allChat();
    }

    //    获得指定用户ID的评论列表
    @RequestMapping(value = "/chat/user/detail", method = RequestMethod.GET)
    public Object chatOfUserId(HttpServletRequest req){
        String userId = req.getParameter("userId");
        return chatService.chatOfUserId(Integer.parseInt(userId));
    }



//    //    点赞
//    @ResponseBody
//    @RequestMapping(value = "/comment/like", method = RequestMethod.POST)
//    public Object commentOfLike(HttpServletRequest req){
//
//        JSONObject jsonObject = new JSONObject();
//        String id = req.getParameter("id").trim();
//        String up = req.getParameter("up").trim();
//
//        Comment comment = new Comment();
//        comment.setId(Integer.parseInt(id));
//        comment.setUp(Integer.parseInt(up));
//        boolean res = commentService.updateCommentMsg(comment);
//        if (res){
//            jsonObject.put("code", 1);
//            jsonObject.put("msg", "点赞成功");
//            return jsonObject;
//        }else {
//            jsonObject.put("code", 0);
//            jsonObject.put("msg", "点赞失败");
//            return jsonObject;
//        }
//    }

    //    删除帖子
    @RequestMapping(value = "/chat/delete", method = RequestMethod.GET)
    public Object deleteChat(HttpServletRequest req){
        String id = req.getParameter("id");
        return chatService.deleteChat(Integer.parseInt(id));
    }

    //    更新帖子
    @ResponseBody
    @RequestMapping(value = "/chat/update", method = RequestMethod.POST)
    public Object updateChatMsg(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String user_id = req.getParameter("userId").trim();
        String title = req.getParameter("title").trim();
        String content = req.getParameter("content").trim();

        Chat chat = new Chat();
        chat.setId(Integer.parseInt(id));
        chat.setUserId(Integer.parseInt(user_id));
        chat.setTitle(title);
        chat.setContent(content);

        boolean res = chatService.updateChatMsg(chat);
        if (res){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "修改成功");
            return jsonObject;
        }else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "修改失败");
            return jsonObject;
        }
    }
}

