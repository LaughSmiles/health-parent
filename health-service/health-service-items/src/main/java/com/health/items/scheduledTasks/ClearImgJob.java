package com.health.items.scheduledTasks;


import com.alibaba.fastjson.JSON;
import com.health.items.utils.FastDFSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class ClearImgJob {

    @Autowired
    private RedisTemplate redisTemplate;

    /****
     * 每个小时执行一次
     */
    @Scheduled(cron = "59 * * * * ?")
    public void loadGoodsPushRedis(){
        //计算插值
        //用户上传的图片
        Set<String> setmealPicResources = redisTemplate.boundSetOps("setmealPicResources").members();
        //用于确定递交的图片
        Set<String> setmealPicDbResources = redisTemplate.boundSetOps("setmealPicDbResources").members();
        //得到差值
        setmealPicResources.removeAll(setmealPicDbResources);

        for (String sub : setmealPicResources) {
            //剔除redis中setmealPicResources的插值部分
            redisTemplate.boundSetOps("setmealPicResources").remove(sub);

            //删除fastDfs中多余的图片
            List<String> subList = JSON.parseArray(sub, String.class);
            String groupName = subList.get(0);
            String remoteFileName = subList.get(1);
            FastDFSClient.deleteFile(groupName, remoteFileName);
        }
    }
}
