package com.example.dramaserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.dramaserver.domain.CateDrama;
import com.example.dramaserver.service.impl.CateDramaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Controller
public class CateDramaController {

    @Autowired
    private CateDramaServiceImpl cateDramaService;

    //    给各类目下添加美剧
    @ResponseBody
    @RequestMapping(value = "/CateDrama/add", method = RequestMethod.POST)
    public Object addCateDrama(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String drama_id = req.getParameter("dramaId").trim();
        String drama_cate_id = req.getParameter("dramaCateId").trim();

        CateDrama cateDrama = new CateDrama();
        cateDrama.setDramaId(Integer.parseInt(drama_id));
        cateDrama.setDramaCateId(Integer.parseInt(drama_cate_id));

        boolean res = cateDramaService.addCateDrama(cateDrama);
        if (res){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "添加成功");
            return jsonObject;
        }else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "添加失败");
            return jsonObject;
        }
    }

    //    返回该类目下包含的所有美剧
    @RequestMapping(value = "/cateDrama", method = RequestMethod.GET)
    public Object allCateDrama(){
        return cateDramaService.allCateDrama();
    }

    //    返回类目下指定美剧ID的美剧
    @RequestMapping(value = "/cateDrama/detail", method = RequestMethod.GET)
    public Object cateDramaOfDramaId(HttpServletRequest req){
        String dramaCateId = req.getParameter("dramaCateId");
        return cateDramaService.cateDramaOfDramaId(Integer.parseInt(dramaCateId));
    }

    //    删除类目下的美剧
    @RequestMapping(value = "/cateDrama/delete", method = RequestMethod.GET)
    public Object deleteCateDrama(HttpServletRequest req){
        String dramaId = req.getParameter("dramaId");
        return cateDramaService.deleteCateDrama(Integer.parseInt(dramaId));
    }

    //    更新类目下的美剧信息
    @ResponseBody
    @RequestMapping(value = "/cateDrama/update", method = RequestMethod.POST)
    public Object updateCateDramaMsg(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String drama_id = req.getParameter("dramaId").trim();
        String drama_cate_id = req.getParameter("dramaCateId").trim();

        CateDrama cateDrama = new CateDrama();
        cateDrama.setId(Integer.parseInt(id));
        cateDrama.setDramaId(Integer.parseInt(drama_id));
        cateDrama.setDramaCateId(Integer.parseInt(drama_cate_id));

        boolean res = cateDramaService.updateCateDramaMsg(cateDrama);
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


