package com.team01.douban.service.impl;

import com.team01.douban.dao.UserMapper;
import com.team01.douban.entity.User;
import com.team01.douban.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public User addUser(User user) {

        if (mapper.selectByUid(user.getUid()) == null) {
            user.setState(0);
            mapper.addUser(user);
            return user;
        }
        return null;
    }

    @Override
    public int login(String uid, String pwd) {

        User user = mapper.login(uid, pwd);
        User user2=mapper.LoginSelectUid(uid);
        if (user != null && user.getState() == 1)
            return 1;//可以成功登陆，返回1
        else if (user2.getUid() != null && user2.getPwd()!= pwd) {
            return 2;//密码输入错误
        }

        else if(user2.getUid()==null){
            return 3;//用户名不存在
        }
        return 0;



//        User useruid =mapper.LoginSelectUid(uid);
//        User userpwd = mapper.LoginSelectPwd(pwd);
//        if (useruid !=null && userpwd !=null ){
//            return 1;//用户名密码输入正确
//        }
//
//        else if (useruid != null ){
//            if (userpwd == null) {
//                return 2;//密码输入错误
//            }
//        }
//        else{
//            return 3;//用户名不存在
//        }
//
//        return 0;

    }

    @Override
    public List<User> search(User user, int page, int limit) {
        //如果非空而且不是一个空串
        if (user != null && !"".equals(user.getUid().trim())){
            user.setUid("%" + user.getUid() + "%");
        }

        if (page > 0 && limit > 0)
            return mapper.selectByWhere(user, (page - 1) * limit, limit);

        return mapper.selectByWhere(user,null,null);
    }

    @Override
    public int searchCount(User user) {
        return mapper.countSelectByWhere(user);
    }


    @Override
    public boolean resetPwd(int id){
        String defaultPwd = "000000";  //默认密码

        if (mapper.updatePwd(id, defaultPwd)==1)
            return true;

        return false;
    }

}
