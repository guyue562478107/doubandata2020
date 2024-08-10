package com.team01.douban.entity;

import lombok.Data;

@Data
public class User {

    private int id;
    private String uid;
    private String pwd;
    private String nickName;
    private int state;

    public  User(String uid, String pwd){
        this.uid = uid;
        this.pwd = pwd;
    }
}
