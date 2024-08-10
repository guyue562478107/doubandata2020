package com.team01.douban.service.impl;

import com.team01.douban.dao.BookMapper;
import com.team01.douban.entity.Book;
import com.team01.douban.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper mapper;

    @Override
    public List<Book> search(Book book, int page, int limit) {

        if (book != null && !"".equals(book.getBookName().trim())) {
            book.setBookName("%" + book.getBookName() + "%");
        }

        if (book != null && !"".equals(book.getBookAuthor().trim())) {
            book.setBookAuthor("%" + book.getBookAuthor() + "%");
        }
        if (page > 0 && limit > 0) {
            return mapper.selectByWhere(book, (page - 1) * limit, limit);
        }
        return mapper.selectByWhere(book, null, null);

    }

    @Override
    public Book findBookInfo(String name) {
        return mapper.selectByName(name);
    }

    @Override
    public boolean updateBookInfo(Book book) {
        if (book != null && book.getBookName() != null ){
            Book oldBookInfo =  mapper.selectByName(book.getBookName());
            if (oldBookInfo != null){
                oldBookInfo.setBookAuthor(book.getBookAuthor());
                return mapper.update(oldBookInfo) == 1 ? true : false;
            }
        }
        return false;
    }

    @Override
    public List<Book> searchall(Book book,int page, int limit){
        if (page > 0 && limit > 0){
            return mapper.selectAll((page-1) * limit, limit);
        }
        return mapper.selectAll(null,null);
    }
}



