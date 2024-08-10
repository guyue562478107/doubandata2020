package com.team01.douban.dao;


import com.team01.douban.entity.Movie;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieMapper {
    @Select("select movie_name, movie_director, movie_date, movie_category, movie_star, movie_vote_num from yhl_doubanmovie limit 0, 30")
    public List<Movie> selectAll();

    @Select("<script>"+
            "select movie_name, movie_director, movie_date , movie_category, movie_star, movie_vote_num"+
            " from yhl_doubanmovie"+
            "<where>"+
            "   <if test='movie.movieName != null and movie.movieName.length > 0'>"+
            "       and movie_name like #{movie.movieName}"+
            "   </if>"+
            "   <if test='movie.movieDirector != null and movie.movieDirector.length > 0'>" +
            "       and movie_director like #{movie.movieDirector}"+
            "   </if>"+
            "   <if test='movie.movieDate != null and movie.movieDate.length > 0'>" +
            "   and movie_date like #{movie.movieDate}"+
            "   </if>"+
            "</where>" +
            "</script>")
    public List<Movie> selectByWhere(@Param("movie") Movie movie,
                                     @Param("start") Integer start,
                                     @Param("limit") Integer limit);

    @Select("select movie_name, movie_director, movie_date, movie_category, movie_star, movie_vote_num from yhl_doubanmovie where movie_name = #{movie_name}")
    public Movie selectByName(@Param("movie_name") String name);

    @Update("UPDATE yhl_doubanmovie" +
            "   SET movie_director = #{movie.movieDirector}," +
            "       movie_date = #{movie.movieDate}," +
            "       movie_category = #{movie.movieCategory}," +
            "       movie_star = #{movie.movieStar}," +
            "       movie_vote_num = #{movie.movieVoteNum}," +
            " WHERE movie_name = #{movie.movieName}")
    int update(@Param("movie") Movie movie);


    @Select("select movie_star,count(1)as count" +
            " from yhl_doubanmovie " +
            " group by movie_star" +
            " order by movie_star +0 desc")
    public List<Movie> countByStar();

    @Select("<script>"+
            "select movie_date, movie_star " +
            " from yhl_doubanmovie " +
            "<where>" +
            "<if test='movie.movieDirector != null and movie.movieDirector.length > 0'>" +
            "    and movie_director like #{movie.movieDirector}" +
            "</if>" +
            "</where>" +
            "   order by movie_date" +
            "</script>")
    public List<Movie> countByDirector(@Param("movie") Movie movie);

}
