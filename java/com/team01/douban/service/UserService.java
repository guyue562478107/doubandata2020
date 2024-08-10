package com.team01.douban.service;

import com.team01.douban.entity.User;

import java.util.List;

public interface UserService {
    User addUser(User user);

    int login(String uid, String pwd);

    List<User> search(User user, int page, int limit);

    int searchCount(User user); //新建的统计条目数的

    boolean resetPwd(int id);
}


