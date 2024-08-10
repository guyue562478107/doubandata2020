package com.team01.douban.service.impl;

import com.team01.douban.dao.MovieMapper;
import com.team01.douban.entity.Movie;
import com.team01.douban.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Service实现的主要作用：
 * 1. 具体实现业务逻辑（完成具体工作）
 * 2. 调用mapper层
 */

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieMapper mapper;


    @Override
    public List<Movie> search(Movie movie, int page, int limit) {

        if (movie != null && !"".equals(movie.getMovieName().trim())){
            movie.setMovieName("%" + movie.getMovieName() + "%");
        }

        if (movie != null && !"".equals(movie.getMovieDirector().trim())){
            movie.setMovieDirector("%" + movie.getMovieDirector() + "%");
        }

        if (movie != null && !"".equals(movie.getMovieDate().trim())){
            movie.setMovieDate("%" + movie.getMovieDate() + "%");
        }

        if (page > 0 && limit > 0)
            return mapper.selectByWhere(movie, (page - 1) * limit, limit);

        return mapper.selectByWhere(movie,null,null);
    }

    @Override
    public List<Movie> searchall(Movie movie) {
        return mapper.selectAll();
    }

    @Override
    public Movie findMovieInfo(String name) {
        return mapper.selectByName(name);
    }


    @Override
    public boolean updateMovieInfo(Movie movie) {

        if (movie != null && movie.getMovieName() != null ){
            Movie oldMovieInfo = mapper.selectByName(movie.getMovieName());
            if (oldMovieInfo != null){
                oldMovieInfo.setMovieDate(movie.getMovieDate());
                oldMovieInfo.setMovieVoteNum(movie.getMovieVoteNum());
                return mapper.update(oldMovieInfo) == 1 ? true : false;
            }
        }
        return false;
    }

    @Override
    public List<Movie> starcount() {
        return mapper.countByStar();
    }


    @Override
    public List<Movie> directorDate(Movie movie){

        return mapper.countByDirector(movie);
    }
}
