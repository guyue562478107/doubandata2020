package com.team01.douban.service;

import com.team01.douban.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> search(Movie movie, int page, int limit);

    List<Movie> searchall(Movie movie);

    Movie findMovieInfo(String id);

    boolean updateMovieInfo(Movie movie);

    List<Movie> starcount();

    List<Movie> directorDate(Movie movie);
}
