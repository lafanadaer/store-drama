package com.example.dramaserver.controller;

import com.alibaba.fastjson.JSONObject;

import com.example.dramaserver.domain.Rank;
import com.example.dramaserver.service.impl.RankServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Controller
public class RankController {

    @Autowired
    private RankServiceImpl rankService;

    //    提交评分
    @ResponseBody
    @RequestMapping(value = "/rank/add", method = RequestMethod.POST)
    public Object addRank(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String dramaId = req.getParameter("dramaId").trim();
        String userId = req.getParameter("userId").trim();
        String score = req.getParameter("score").trim();

        Rank rank = new Rank();
        rank.setDramaId(Long.parseLong(dramaId));
        rank.setUserId(Long.parseLong(userId));
        rank.setScore(Integer.parseInt(score));

        boolean res = rankService.addRank(rank);
        if (res){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "评价成功");
            return jsonObject;
        }else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "评价失败");
            return jsonObject;
        }
    }

    //    获取指定类目的评分
    @RequestMapping(value = "/rank", method = RequestMethod.GET)
    public Object rankOfDramaId(HttpServletRequest req){
        String dramaId = req.getParameter("dramaId");
        return rankService.rankOfDramaId(Long.parseLong(dramaId));
    }
}
