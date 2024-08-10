package com.team01.douban.controller;

import com.team01.douban.entity.Book;
import com.team01.douban.service.BookService;
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
 * 1.接收用户信息
 * 2.调用service层（调用业务逻辑）
 * 3.接收service返回数据并根据返回结果进行数据组装或页面跳转
 */

@Controller
@RequestMapping("logic/book")
public class BookController {

    @Autowired
    private BookService service;

    @RequestMapping(value = "/search")
    @ResponseBody
    public TableData search(Book book, int page, int limit){

        TableData data = new TableData();
        List<Book> result = service.search(book,page,limit);
        data.setCode(0);
        data.setMsg("");
        data.setCount(result.size());
        data.setData(result);

        return data;
    }

    @RequestMapping("/{bookName}")
    @ResponseBody
    public Book info(@PathVariable("bookName") String name){

        Book book = service.findBookInfo(name);

        return book;
    }

    @RequestMapping("update")
    @ResponseBody
    public Message update(Book book){
        Message msg = new Message();

        msg.setError(true);
        msg.setContent("服务器错误");

        if (book != null && book.getId() != null){
            if (service.updateBookInfo(book)){
                msg.setError(false);
                msg.setContent("更新完成");
                return msg;
            }
        }

        return msg;
    }

    @RequestMapping(value = "/search all")
    @ResponseBody
    public TableData searchall(Book book, int page, int limit){
        TableData data =new TableData();
        List<Book> result = service.searchall(book,page,limit);
        data.setCode(0);
        data.setMsg("");
        data.setCount(result.size());
        data.setData(result);

        return data;
    }

}
