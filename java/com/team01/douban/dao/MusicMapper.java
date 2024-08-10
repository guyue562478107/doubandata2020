package com.team01.douban.dao;

import com.team01.douban.entity.Music;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicMapper {
    @Select("select music_name, music_singer, music_time, music_type, music_score" +
            " from guyuemusictable "+
            "limit #{start}, #{limit}")
    public List<Music> selectAll(@Param("start") Integer start, @Param("limit") Integer limit);

    @Select("select count(1)" +
            " from guyuemusictable"
            )
    public int selectallcount();

    @Select("<script>"+
            "select music_name, music_singer, music_time , music_type, music_score"+
            " from guyuemusictable"+
            "<where>"+
            "   <if test='music.musicTime != null and music.musicTime.length > 0'>" +
            "       and music_time like #{music.musicTime}" +
            "   </if>" +
            "   <if test='music.musicSinger != null and music.musicSinger.length > 0'>" +
            "       and music_singer like #{music.musicSinger}"+
            "   </if>"+
            "   <if test='music.musicType != null and music.musicType.length > 0'>"+
            "       and music_type like #{music.musicType}"+
            "   </if>" +
            "</where>"+
            "<if test='start != null and limit != null'>"+
            "   limit #{start}, #{limit}"+
            "</if>"+
            "</script>")
    public List<Music> selectByWhere(@Param("music") Music music,
                                     @Param("start") Integer start,
                                     @Param("limit") Integer limit);

    @Select("<script>"+
            "select count(1)"+
            " from guyuemusictable"+
            "<where>"+
            "   <if test='music.musicTime != null and music.musicTime.length > 0'>" +
            "       and music_time like #{music.musicTime}" +
            "   </if>" +
            "   <if test='music.musicSinger != null and music.musicSinger.length > 0'>" +
            "       and music_singer like #{music.musicSinger}"+
            "   </if>"+
            "   <if test='music.musicType != null and music.musicType.length > 0'>"+
            "       and music_type like #{music.musicType}"+
            "   </if>"+
            "</where>"+
            "</script>")
    int countSelectByWhere(@Param("music") Music music);

    @Select("select music_score,count(1)as count" +
            " from guyuemusictable " +
            " group by music_score" +
            " order by music_score +0 desc")
    public List<Music> countByScore();

    @Select("select count(1) as count,music_type\n" +
            "from guyuemusictable\n" +
            "group by music_type")
    public List<Music> countByType();


    @Select("<script>"+
            "select music_time, music_score " +
            " from guyuemusictable " +
            "<where>" +
            "<if test='music.musicSinger != null and music.musicSinger.length > 0'>" +
            "    and music_singer like #{music.musicSinger}" +
            "</if>" +
            "</where>" +
            "   order by music_time" +
            "</script>")
    public List<Music> countBySinger(@Param("music") Music music);
}
