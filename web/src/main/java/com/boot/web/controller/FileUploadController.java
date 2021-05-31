package com.boot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
public class FileUploadController {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    @PostMapping("/upload")
    public String upload(@RequestParam(value="file") MultipartFile[] file, HttpServletRequest req){
        System.out.println("进入文件上传操作！！！");
        String realPath = req.getServletContext().getRealPath("/uploadFile/");
        String format = sdf.format(new Date());
        File folder = new File(realPath + format);
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        for (int i = 0; i < file.length; i++) {
            String oldName = file[i].getOriginalFilename();
            String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."),oldName.length());
            try {
                file[i].transferTo(new File(folder,newName));
                String filePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/uploadFile/" + format + newName;
                return filePath;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "上传失败！";
    }


    @GetMapping("/toUpload")
    public ModelAndView toUpload(){
        ModelAndView mv = new ModelAndView("upload2");
        return mv;
    }

}
