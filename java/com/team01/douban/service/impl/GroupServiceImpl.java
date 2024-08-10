package com.team01.douban.service.impl;

import com.team01.douban.dao.GroupMapper;
import com.team01.douban.entity.Group;
import com.team01.douban.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMapper mapper;

    @Override
    public List<Group> searchAll(Group group,int page,int limit){ return mapper.selectAll(group,(page-1)*limit, limit);
    }

    @Override
    public List<Group> search(Group group,int page,int limit) {

//        if(group!=null && "".equals(group.getArticle().trim()))
//            group.setArticle();
//
        if(page > 0 && limit > 0)
            return mapper.select(group,(page-1)*limit, limit);

        return mapper.select(group,null, null);
    }

    @Override
    public int searchCount(Group group) {
        return mapper.countselect(group);
    }
}
