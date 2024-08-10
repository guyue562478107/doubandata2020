package com.team01.douban.dao;

import com.team01.douban.entity.Drama;
import com.team01.douban.entity.Music;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DramaMapper {

    @Select("<script>" +   //不是javas 代表当前select里不是纯粹的sql语句而是mybatis脚本（尖括号）
            "SELECT id, tv_title, tv_score, tv_director, tv_actors, tv_type, tv_region, tv_time" +
            "   FROM lxy_tv_test" +
            "<where>" +    //如果有一个成立  就会自动生成where子句（后缀）
            "    <if test='drama.tvTitle != null and drama.tvTitle.length > 0'>" + //test里面必须写布尔表达式，是if的条件  //字符串里要写and不能用&&
            "      AND tv_title like #{drama.tvTitle}" +            //规范的不使用$纯替换而使用#就要在service里下手   //用这个！！！！
            "    </if>" +
     /*     "    <if test='user.nickName != null and user.nickName.length > 0'>" +
            "      AND nick_name like '%${user.nickName}%'" +    // ！！注意单引号'（字符串）'  如果使用$不带防依赖，只是简单的替换，相当于replace
            "    </if>" +           //为了拼接前后的模糊查询  百分号%要与like配合使用   不推荐！！！！
            "    <if test='user.state != null and user.state != -1'>" +
            "      AND state = #{user.state}" +   //如果用# 使用的是preparies statement 预处理sql语句占位符功能
            "    </if>" + */
            "    <if test='drama.tvActors != null and drama.tvActors.length > 0'>" +
            "      AND tv_actors like #{drama.tvActors}" +
            "    </if>" +
            "    <if test='drama.tvRegion != null and drama.tvRegion.length > 0'>" +
            "      AND tv_region like #{drama.tvRegion}" +
            "    </if>" +
            "</where>" +
            "<if test='start != null and limit != null'>" +
            "    limit #{start}, #{limit}" +
            "</if>" +
            "</script>")
    public List<Drama> selectByWhere(@Param("drama") Drama user,
                                     @Param("start") Integer start,
                                     @Param("limit") Integer limit);


    @Select("<script>" +
            "SELECT count(1)" +
            "   FROM lxy_tv_test" +
            "<where>" +
            "    <if test='drama.tvTitle != null and drama.tvTitle.length > 0'>" +
            "      AND tv_title like #{drama.tvTitle}" +
            "    </if>" +
            "    <if test='drama.tvActors != null and drama.tvActors.length > 0'>" +
            "      AND tv_actors like #{drama.tvActors}" +
            "    </if>" +
            "    <if test='drama.tvRegion != null and drama.tvRegion.length > 0'>" +
            "      AND tv_region like #{drama.tvRegion}" +
            "    </if>" +
            "</where>" +
            "</script>")
    int countSelectByWhere(@Param("drama") Drama drama);

    @Select("select id, tv_title, tv_score, tv_director, tv_actors, tv_type, tv_region, tv_time  from lxy_tv_test where id = #{id}")
    public Drama selectById(@Param("id") String id);

    @Update("UPDATE lxy_tv_test" +
            "   SET tv_title = #{drama.tvTitle}," +
            "       tv_score = #{drama.tvScore}," +
            "       tv_director = #{drama.tvDirector}," +
            "       tv_actors = #{drama.tvActors}," +
            "       tv_type = #{drama.tvType}," +
            "       tv_region = #{drama.tvRegion}," +
            "       tv_time = #{drama.tvTime}" +
            " WHERE id = #{drama.id}")
    int update(@Param("drama") Drama drama);

    @Select("select tv_score,count(1)as count" +
            " from lxy_tv_test " +
            " group by tv_score" +
            " order by tv_score + 0 asc")
    public List<Drama> countByScore();

    @Select("select type as type,num as count" +
            " from lxy_type " +
            " order by num asc")
    public List<Drama> countByType();

    @Select("select language as language,l_num as count" +
            " from lxy_language " +
            " order by l_num desc")
    public List<Drama> countByLanguage();

    @Select("<script>"+
            "select tv_time, tv_score " +
            " from lxy_tv_test " +
            "<where>" +
            "    <if test='drama.tvActors != null and drama.tvActors.length > 0'>" +
            "      AND tv_actors like #{drama.tvActors}" +
            "</if>" +
            "</where>" +
            "   order by tv_time" +
            "</script>")
    public List<Drama> countByActor(@Param("drama") Drama drama);

    @Select("select tv_time ,tv_score " +
            " from lxy_tv_test " +
            " order by tv_time asc")
    public List<Drama> timeAndScore();



}
