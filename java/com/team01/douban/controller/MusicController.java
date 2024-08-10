package com.team01.douban.controller;

import com.team01.douban.entity.Music;
import com.team01.douban.service.MusicService;
import com.team01.douban.tools.TableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/logic/music")
@Controller
public class MusicController {

    @Autowired
    private MusicService service;


    @RequestMapping(value = "/search")
    @ResponseBody
    public TableData search(Music music, int page, int limit){

        TableData data =new TableData();
        List<Music> result = service.search(music,page,limit);
        data.setCode(0);
        data.setMsg("");
        data.setCount(service.searchcount(music));
        data.setData(result);


        return data;
    }


    @RequestMapping(value = "/search all")
    @ResponseBody
    public TableData searchall(Music music, int page, int limit){

        TableData data =new TableData();
        List<Music> result = service.searchall(music,page,limit);
        data.setCode(0);
        data.setMsg("");
        data.setCount(service.searchallcount());
        data.setData(result);

        return data;
    }



    @RequestMapping(value = "/scorecount")
    @ResponseBody
    public List<Music> musicscorecount(){
        
        return service.scorecount();
    }

    @RequestMapping(value = "/typecount")
    @ResponseBody
    public List<Music> musictypecount(){
        return service.typecount();
    }

    @RequestMapping(value = "/singerTime")
    @ResponseBody
    public List<Music> musicsinger(Music music){

        return service.singerTime(music);
    }

}
