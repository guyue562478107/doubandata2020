package com.team01.douban.service;

import com.team01.douban.entity.Group;

import java.util.List;

public interface GroupService {
    List<Group> searchAll(Group group,int page,int limit);

    List<Group> search(Group group ,int page,int limit);

    int searchCount(Group group);
}
