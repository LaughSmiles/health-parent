package com.health.items.controller;

import com.alibaba.fastjson.JSON;
import com.health.items.utils.FastDFSClient;
import com.health.items.utils.FastDFSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.spring.web.json.Json;

@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileController {

    @Autowired
    private RedisTemplate redisTemplate;

    /***
     * 文件上传
     * @return
     */
    @PostMapping(value = "/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws Exception {
        //封装一个FastDFSFile
        FastDFSFile fastDFSFile = new FastDFSFile(
                file.getOriginalFilename(), //文件名字
                file.getBytes(),            //文件字节数组
                StringUtils.getFilenameExtension(file.getOriginalFilename()));//文件扩展名
        //文件上传
        String[] uploads = FastDFSClient.upload(fastDFSFile);

        //将图片的的路径保存到redis中
        redisTemplate.boundSetOps("setmealPicResources").add(JSON.toJSONString(uploads));

        //组装文件上传地址
        return FastDFSClient.getTrackerUrl()+"/"+uploads[0]+"/"+uploads[1];
    }
}
