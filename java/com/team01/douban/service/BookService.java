package com.team01.douban.service;

import com.team01.douban.entity.Book;


import java.util.List;

public interface BookService {
    List<Book> search(Book book, int page, int limit);
    List<Book> searchall(Book book, int page, int limit);
    boolean updateBookInfo(Book book);
    Book findBookInfo(String id);


}
