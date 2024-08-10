package com.team01.douban.controller;

import com.team01.douban.entity.Movie;
import com.team01.douban.service.MovieService;
import com.team01.douban.tools.Message;
import com.team01.douban.tools.TableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Controller的主要作用
 * 1. 接收用户输入信息
 * 2. 调用Service层（调用业务逻辑）
 * 3. 接收Service返回数据并根据返回结果进行数据组装或页面跳转  按照前端要求把数据组装成他想要的样子
 */


@RequestMapping("/logic/movie")
@Controller
public class MovieController {

    @Autowired
    private MovieService service;


    @RequestMapping(value = "/search")
    @ResponseBody
    public TableData search(Movie movie, int page, int limit){

        TableData data =new TableData();
        List<Movie> result = service.search(movie, page, limit);
        data.setCode(0);
        data.setMsg("");
        data.setCount(result.size());
        data.setData(result);


        return data;
    }


    @RequestMapping(value = "/search all")
    @ResponseBody
    public TableData searchall(Movie movie){

        TableData data =new TableData();
        List<Movie> result = service.searchall(movie);
        data.setCode(0);
        data.setMsg("");
        data.setCount(result.size());
        data.setData(result);

        return data;
    }

    /**
     * REST风格-值传递
     * @param name
     * @return
     */

    @RequestMapping("/{movieName}")
    @ResponseBody
    public Movie info(@PathVariable("movieName") String name){

        Movie movie = service.findMovieInfo(name);

        return movie;
    }


    @RequestMapping("update")
    @ResponseBody
    public Message update(Movie movie){
        Message msg = new Message();

        msg.setError(true);
        msg.setContent("服务器错误");

        if (movie != null && movie.getMovieName() != null){
            if (service.updateMovieInfo(movie)){
                msg.setError(false);
                msg.setContent("已经更新完成");
                return msg;
            }
        }

        return msg;
    }

    @RequestMapping(value = "/starcount")
    @ResponseBody
    public List<Movie> moviestarcount(){

        return service.starcount();
    }


    @RequestMapping(value = "/directorDate")
    @ResponseBody
    public List<Movie> moviedirector(Movie movie){

        return service.directorDate(movie);
    }

}
