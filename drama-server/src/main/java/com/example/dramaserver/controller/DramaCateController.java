package com.example.dramaserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.dramaserver.domain.DramaCate;
import com.example.dramaserver.service.impl.DramaCateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;


@RestController
@Controller
public class DramaCateController {

    @Autowired
    private DramaCateServiceImpl dramaCateService;

    @Configuration
    public class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/dramaCatePic/**").addResourceLocations("file:E:/blogboot/music-website-master/music-server/data/img/songListPic/");
        }
    }

    //    添加类目
    @ResponseBody
    @RequestMapping(value = "/dramaCate/add", method = RequestMethod.POST)
    public Object addDramaCate(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String name = req.getParameter("name").trim();
        String pic = req.getParameter("pic").trim();
        String introduction = req.getParameter("introduction").trim();

        DramaCate dramaCate = new DramaCate();
        dramaCate.setName(name);
        dramaCate.setPic(pic);
        dramaCate.setIntroduction(introduction);

        boolean res = dramaCateService.addDramaCate(dramaCate);
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

    //    返回所有分类
    @RequestMapping(value = "/dramaCate", method = RequestMethod.GET)
    public Object allDramaCate(){
        return dramaCateService.allDramaCate();
    }

    //    返回指定类名对应的分类
    @RequestMapping(value = "/dramaCate/name/detail", method = RequestMethod.GET)
    public Object songListOfTitle(HttpServletRequest req){
        String name = req.getParameter("name").trim();
        return dramaCateService.dramaCateOfName(name);
    }

    //    返回类名包含文字的美剧类目 可以删除
    @RequestMapping(value = "/dramaCate/likeName/detail", method = RequestMethod.GET)
    public Object dramaCateOfLikeName(HttpServletRequest req){
        String name = req.getParameter("name").trim();
        return dramaCateService.likeName('%'+ name + '%');
    }

    //    删除类目
    @RequestMapping(value = "/dramaCate/delete", method = RequestMethod.GET)
    public Object deleteDramaCate(HttpServletRequest req){
        String id = req.getParameter("id");
        return dramaCateService.deleteDramaCate(Integer.parseInt(id));
    }

    //    更新类目信息
    @ResponseBody
    @RequestMapping(value = "/dramaCate/update", method = RequestMethod.POST)
    public Object updateDramaCateMsg(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String name = req.getParameter("name").trim();
        String pic = req.getParameter("pic").trim();
        String introduction = req.getParameter("introduction").trim();

        DramaCate dramaCate = new DramaCate();
        dramaCate.setId(Integer.parseInt(id));
        dramaCate.setName(name);
        dramaCate.setPic(pic);
        dramaCate.setIntroduction(introduction);

        boolean res = dramaCateService.updateDramaCateMsg(dramaCate);
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

    //    更新类目图片
    @ResponseBody
    @RequestMapping(value = "/dramaCate/img/update", method = RequestMethod.POST)
    public Object updateDramaCatePic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id")int id){
        JSONObject jsonObject = new JSONObject();

        if (avatorFile.isEmpty()) {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "文件上传失败！");
            return jsonObject;
        }
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "dramaCatePic" ;
        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeAvatorPath = "/img/dramaCatePic/"+fileName;
        try {
            avatorFile.transferTo(dest);
            DramaCate dramaCate = new DramaCate();
            dramaCate.setId(id);
            dramaCate.setPic(storeAvatorPath);
            boolean res = dramaCateService.updateDramaCatePic(dramaCate);
            if (res){
                jsonObject.put("code", 1);
                jsonObject.put("avator", storeAvatorPath);
                jsonObject.put("msg", "上传成功");
                return jsonObject;
            }else {
                jsonObject.put("code", 0);
                jsonObject.put("msg", "上传失败");
                return jsonObject;
            }
        }catch (IOException e){
            jsonObject.put("code", 0);
            jsonObject.put("msg", "上传失败" + e.getMessage());
            return jsonObject;
        }finally {
            return jsonObject;
        }
    }
}


