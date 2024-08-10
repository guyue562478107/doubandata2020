package com.team01.douban.controller;

import com.team01.douban.entity.Drama;
import com.team01.douban.entity.Music;
import com.team01.douban.tools.Message;
import com.team01.douban.tools.TableData;
import com.team01.douban.service.DramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Controller的主要作用
 * 1. 接收用户输入信息
 * 2. 调用Service层（调用业务逻辑）
 * 3. 接收Service返回数据并根据返回结果进行数据组装或页面跳转  按照前端要求把数据组装成他想要的样子
 */

@RequestMapping("/logic/drama")
@Controller
public class DramaController {

    @Autowired
    private DramaService service;

    @RequestMapping(value = "/search")
    @ResponseBody
    public TableData search(Drama drama, int page, int limit) {

        TableData data = new TableData();
        List<Drama> result = service.search(drama, page, limit);
        data.setCode(0);
        data.setMsg("");
        data.setCount(service.searchCount(drama));
        data.setData(result);

        return data;
    }

    /**
     * REST风格-值传递
     * @param id
     * @return
     */

    @RequestMapping("/{id}")
    @ResponseBody
    public Drama info(@PathVariable("id") String id){

        Drama drama = service.findDramaInfo(id);

        return drama;
    }

    @RequestMapping("update")
    @ResponseBody
    public Message update(Drama drama){
        Message msg = new Message();

        msg.setError(true);
        msg.setContent("服务器错误");

        if (drama != null && drama.getId() != null){
            if (service.updateDramaInfo(drama)){
                msg.setError(false);
                msg.setContent("已经更新完成");
                return msg;
            }
        }

        return msg;
    }

    @RequestMapping(value = "/count/scorecount")
    @ResponseBody
    public List<Drama> dramascorecount(){

        return service.scoreCount();
    }

    @RequestMapping(value = "/count/typecount")
    @ResponseBody
    public List<Drama> dramatypecount(){

        return service.typeCount();
    }

    @RequestMapping(value = "/count/languagecount")
    @ResponseBody
    public List<Drama> dramalanguagecount(){

        return service.languageCount();
    }

    @RequestMapping(value = "/actorTime")
    @ResponseBody
    public List<Drama> dramaactor(Drama drama){

        return service.actorTime(drama);
    }

    @RequestMapping(value = "/count/timescore")
    @ResponseBody
    public List<Drama> dramatimescore(){

        return service.timeScore();
    }


}
