package com.team01.douban.dao;

import com.team01.douban.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserMapper {

    @Select("select * from user where uid = #{uid}")
    public User selectByUid(@Param("uid") String uid);

    @Insert("insert into user(uid,pwd,nick_name,state) " +
            "values(#{user.uid},#{user.pwd}, #{user.nickName}, #{user.state})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int addUser(@Param("user") User user);

    @Select("select * from user where uid = #{uid} and pwd = #{pwd}")
    public User login(@Param("uid") String uid,@Param("pwd") String pwd);


    @Select("select * from user where uid = #{uid}")
    public User LoginSelectUid(@Param("uid") String uid);

//    @Select("select * from user where pwd = #{pwd}")
//    public User LoginSelectPwd(@Param("pwd") String pwd);

    @Update("update user set pwd = #{newPwd} where id = #{id}")
    int updatePwd(@Param("id") int id, @Param("newPwd") String newPwd);

    @Select("select id, uid, nickName, state from user where id = #{id}")
    public User selectById(@Param("id") int id);

    @Select("<script>" +   //不是javas 代表当前select里不是纯粹的sql语句而是mybatis脚本（尖括号）
            "SELECT id, uid, nick_name, state" +
            "   FROM user" +
            "<where>" +    //如果有一个成立  就会自动生成where子句（后缀）
            "    <if test='user.uid != null and user.uid.length > 0'>" + //test里面必须写布尔表达式，是if的条件  //字符串里要写and不能用&&
            "      AND uid like #{user.uid}" +            //规范的不使用$纯替换而使用#就要在service里下手   //用这个！！！！
            "    </if>" +
            "    <if test='user.nickName != null and user.nickName.length > 0'>" +
            "      AND nick_name like '%${user.nickName}%'" +    // ！！注意单引号'（字符串）'  如果使用$不带防依赖，只是简单的替换，相当于replace
            "    </if>" +           //为了拼接前后的模糊查询  百分号%要与like配合使用   不推荐！！！！
            "    <if test='user.state != null and user.state != -1'>" +
            "      AND state = #{user.state}" +   //如果用# 使用的是preparies statement 预处理sql语句占位符功能
            "    </if>" +
            "</where>" +
            "<if test='start != null and limit != null'>" +
            "    limit #{start}, #{limit}" +
            "</if>" +
            "</script>")
    public List<User> selectByWhere(@Param("user") User user,
                                    @Param("start") Integer start,
                                    @Param("limit") Integer limit);


    @Select("<script>" +
            "SELECT count(1)" +
            "   FROM user" +
            "<where>" +
            "    <if test='user.uid != null and user.uid.length > 0'>" +
            "      AND uid like #{user.uid}" +
            "    </if>" +
            "    <if test='user.nickName != null and user.nickName.length > 0'>" +
            "      AND nick_name like '%${user.nickName}%'" +
            "    </if>" +
            "    <if test='user.state != null and user.state != -1'>" +
            "      AND state = #{user.state}" +
            "    </if>" +
            "</where>" +
            "</script>")
    int countSelectByWhere(@Param("user") User user);


}


