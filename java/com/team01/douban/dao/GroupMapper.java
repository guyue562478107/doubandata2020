package com.team01.douban.dao;


import com.team01.douban.entity.Group;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupMapper {


    @Select("<script>" +
            "SELECT *" +
            "  FROM xgh_groupdetail"+
            "<where>" +
            "<if test='group.article != null and group.article.length > 0'>" +
            "    and article like #{group.article}" +
            "</if>" +
            "<if test='group.zuozhe != null and group.zuozhe.length > 0'>" +
            "    and zuozhe like #{group.zuozhe}" +
            "</if>" +
            "</where>" +
            "<if test='start != null and limit != null'>"+
            "   limit #{start}, #{limit}"+
            "</if>"+
            "</script>")
    public List<Group> select(@Param("group") Group group,
                              @Param("start") Integer start,
                              @Param("limit") Integer limit);

    @Select("select * from xgh_groupdetail "+
            "limit #{start}, #{limit}")
    public List<Group> selectAll(@Param("group")Group group,@Param("start") Integer start, @Param("limit") Integer limit);


    @Select("<script>" +
            "SELECT count(1)" +
            "  FROM xgh_groupdetail"+
            "<where>" +
            "<if test='group.article != null and group.article.length > 0'>" +
            "    and article like #{group.article}" +
            "</if>" +
            "</where>" +
            "</script>")
    int countselect(@Param("group") Group group);

}
