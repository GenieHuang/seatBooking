package com.seatbooking.springboot.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.seatbooking.springboot.config.Constants;
import com.seatbooking.springboot.config.Result;
import com.seatbooking.springboot.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/pic")
public class PicController {
    @Value("${server.port}")
    private String port;

    private static final String ip="http://localhost";
//    上传图片
    @PostMapping("/upload")
    public Result<?> uploadPic(@RequestBody MultipartFile file) throws IOException{
        System.out.println("file:::file" + file);
        String originalFilename = file.getOriginalFilename();   //获取源文件的名称
        String flag = IdUtil.fastSimpleUUID();
        String rootFilePath = "D:\\seatBookingOL\\vue\\src\\assets\\" + flag + "_"+originalFilename;    //设置文件路径
        System.out.println(originalFilename.split("\\."));
        try{
            FileUtil.writeBytes(file.getBytes(), rootFilePath);   //把文件写入上传的路径
//            return Result.success(flag + "_"+originalFilename.split("\\.")[0] + '.' + originalFilename.split("\\.")[1]);
            return Result.success(flag + "_"+originalFilename);
//            return Result.success(ip+":"+"8080"+"/files/"+flag);  //返回结果url
        } catch (Exception e){
            return Result.error(Constants.CODE_401,"上传失败");
        }
    }

    /**
     * @param fileName
     * @param response 获取图片的方法
     */
    @GetMapping("/getImage")
    public void getImage(@RequestParam String fileName, HttpServletResponse response) {
        System.out.println("文件名---" + fileName);
        // 这里传图片本地地址，使用的响应留
        String src = "D:\\seatBookingOL\\vue\\src\\assets\\" + fileName;
        System.out.println("文件路径---" + src);

        /*使用ImageUtil的封装方法*/
        ImageUtil.getImageSteam(src, response);
    }


}
