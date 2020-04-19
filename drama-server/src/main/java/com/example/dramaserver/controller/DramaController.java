package com.example.dramaserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.dramaserver.domain.Drama;
import com.example.dramaserver.service.impl.DramaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
@Controller
public class DramaController {
    @Autowired
    private DramaServiceImpl dramaService;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大10M,DataUnit提供5中类型B,KB,MB,GB,TB
        factory.setMaxFileSize(DataSize.of(10, DataUnit.MEGABYTES));
        /// 设置总上传数据总大小10M
        factory.setMaxRequestSize(DataSize.of(10, DataUnit.MEGABYTES));
        return factory.createMultipartConfig();
    }

    @Configuration
    public class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
//            registry.addResourceHandler("/img/dramaPic/**").addResourceLocations("file:E:/blogboot/drama-server/data/img/dramaPic/");
            registry.addResourceHandler("/dramaPic/**").addResourceLocations("file:E:/blogboot/drama-server/data/img/dramaPic/");
        }
    }

    //    更新美剧图片
    @ResponseBody
    @RequestMapping(value = "/drama/img/update", method = RequestMethod.POST)
    public Object updateDramaPic(@RequestParam("file") MultipartFile urlFile, @RequestParam("id")int id){
        JSONObject jsonObject = new JSONObject();

        if (urlFile.isEmpty()) {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "美剧上传失败！");
            return jsonObject;
        }
//        String fileName = System.currentTimeMillis()+urlFile.getOriginalFilename();
        String fileName = urlFile.getOriginalFilename();
//        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "dramaPic";
        String filePath = System.getProperty("user.dir") + "dramaPic";
        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
        }

//        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        File dest = new File(filePath + fileName);
        String storeUrlPath = "/dramaPic/"+fileName;
        try {
            urlFile.transferTo(dest);
            Drama drama = new Drama();
            drama.setId(id);
            drama.setPic(storeUrlPath);
            boolean res = dramaService.updateDramaPic(drama);
            if (res){
                jsonObject.put("code", 1);
                jsonObject.put("pic", storeUrlPath);
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

    //    添加美剧
    @ResponseBody
    @RequestMapping(value = "/drama/add", method = RequestMethod.POST)
    public Object addDrama(HttpServletRequest req/*, @RequestParam("file") MultipartFile mpfile*/){
        JSONObject jsonObject = new JSONObject();
        String category_id = req.getParameter("categoryId").trim();
        String name = req.getParameter("name").trim();
        String introduction = req.getParameter("introduction").trim();
        String pic = "/img/dramaPic/tubiao2.jpg";

//        if (mpfile.isEmpty()) {
//            jsonObject.put("code", 0);
//            jsonObject.put("msg", "美剧上传失败！");
//            return jsonObject;
//        }
//        String fileName = mpfile.getOriginalFilename();
//        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "drama";
//        File file1 = new File(filePath);
//        if (!file1.exists()){
//            file1.mkdir();
//        }
//
//        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
//        String storeUrlPath = "/drama/"+fileName;
        Drama drama = new Drama();
        drama.setCategoryId(Integer.parseInt(category_id));
        drama.setName(name);
        drama.setIntroduction(introduction);
        drama.setCreateTime(new Date());
        drama.setUpdateTime(new Date());
        drama.setPic(pic);
        boolean res = dramaService.addDrama(drama);
        if (res) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "上传成功");
            return jsonObject;
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "上传失败");
            return jsonObject;
        }
    }

    //    返回所有美剧
    @RequestMapping(value = "/drama", method = RequestMethod.GET)
    public Object allDrama(){
        return dramaService.allDrama();
    }

    //    返回指定美剧ID的美剧
    @RequestMapping(value = "/drama/detail", method = RequestMethod.GET)
    public Object dramaOfId(HttpServletRequest req){
        String id = req.getParameter("id");
        return dramaService.dramaOfId(Integer.parseInt(id));
    }

    //    返回指定类目ID的美剧
    @RequestMapping(value = "/drama/category/detail", method = RequestMethod.GET)
    public Object dramaOfCategoryId(HttpServletRequest req){
        String categoryId = req.getParameter("categoryId");
        return dramaService.dramaOfCategoryId(Integer.parseInt(categoryId));
    }

    //    返回指定类目名的美剧
    @RequestMapping(value = "/drama/categoryName/detail", method = RequestMethod.GET)
    public Object dramaOfCategoryName(HttpServletRequest req){
        String name = req.getParameter("name");
        return dramaService.dramaOfCategoryName('%'+ name + '%');
    }

    //    返回指定美剧名的美剧
    @RequestMapping(value = "/drama/name/detail", method = RequestMethod.GET)
    public Object dramaOfName(HttpServletRequest req){
        String name = req.getParameter("name").trim();
        return dramaService.dramaOfName(name);
    }

    //    删除美剧
    @RequestMapping(value = "/drama/delete", method = RequestMethod.GET)
    public Object deleteDrama(HttpServletRequest req){
        String id = req.getParameter("id");
        return dramaService.deleteDrama(Integer.parseInt(id));
    }

    //    更新美剧信息
    @ResponseBody
    @RequestMapping(value = "/drama/update", method = RequestMethod.POST)
    public Object updateDramaMsg(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String category_id = req.getParameter("categoryId").trim();
        String name = req.getParameter("name").trim();
        String introduction = req.getParameter("introduction").trim();

        Drama drama = new Drama();
        drama.setId(Integer.parseInt(id));
        drama.setCategoryId(Integer.parseInt(category_id));
        drama.setName(name);
        drama.setIntroduction(introduction);
        drama.setUpdateTime(new Date());

        boolean res = dramaService.updateDramaMsg(drama);
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



    //    更新美剧URL
//    @ResponseBody
//    @RequestMapping(value = "/song/url/update", method = RequestMethod.POST)
//    public Object updateSongUrl(@RequestParam("file") MultipartFile urlFile, @RequestParam("id")int id){
//        JSONObject jsonObject = new JSONObject();
//
//        if (urlFile.isEmpty()) {
//            jsonObject.put("code", 0);
//            jsonObject.put("msg", "音乐上传失败！");
//            return jsonObject;
//        }
//        String fileName = urlFile.getOriginalFilename();
//        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";
//        File file1 = new File(filePath);
//        if (!file1.exists()){
//            file1.mkdir();
//        }
//
//        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
//        String storeUrlPath = "/song/"+fileName;
//        try {
//            urlFile.transferTo(dest);
//            Song song = new Song();
//            song.setId(id);
//            song.setUrl(storeUrlPath);
//            boolean res = songService.updateSongUrl(song);
//            if (res){
//                jsonObject.put("code", 1);
//                jsonObject.put("avator", storeUrlPath);
//                jsonObject.put("msg", "上传成功");
//                return jsonObject;
//            }else {
//                jsonObject.put("code", 0);
//                jsonObject.put("msg", "上传失败");
//                return jsonObject;
//            }
//        }catch (IOException e){
//            jsonObject.put("code", 0);
//            jsonObject.put("msg", "上传失败" + e.getMessage());
//            return jsonObject;
//        }finally {
//            return jsonObject;
//        }
//    }

}


