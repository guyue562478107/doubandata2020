package com.team01.douban.entity;

import lombok.Data;

@Data
public class Book {

    private String bookName;         //图书名称
    private String bookAuthor;       //图书作者
    private String bookPublish;      //出版社
    private String bookDate;         //出版年
    private String bookPrice;        //图书价格
    private String bookPage;         //页数
    private String bookIsbn;         //图书ISBN
    private String bookBinding;      //图书装帧
    private String bookInterpreter;  //图书译者
    private String bookStar;         //图书评分
    private String bookPl;           //图书评分人数
    private Integer id;

}
