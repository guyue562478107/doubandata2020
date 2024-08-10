package com.team01.douban.controller;


import com.team01.douban.entity.Group;
import com.team01.douban.service.GroupService;
import com.team01.douban.tools.TableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/logic/group")
@Controller
public class GroupController {

    @Autowired
    private GroupService service;

//    public String groupDetail(String )
    @RequestMapping(value = "/search")
    @ResponseBody
    public TableData search(Group group,int page,int limit){
        TableData data = new TableData();
        List<Group> result = service.search(group, page, limit);
        data.setCode(0);

        data.setMsg("");
        data.setCount(service.searchCount(group));
        data.setData(result);

        return data;

    }

    @RequestMapping(value = "/searchAll")
    @ResponseBody
    public TableData searchAll(Group group,int page,int limit){

        TableData data = new TableData();
        List<Group> result = service.searchAll(group,page,limit);
        data.setCode(0);
        data.setMsg("");
        data.setCount(service.searchCount(group));
        data.setData(result);

        return data;
    }
}
