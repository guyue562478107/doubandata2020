package com.team01.douban.dao;

import com.team01.douban.entity.Book;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMapper {

    @Select("<script>" +
            "select book_name, book_author, book_publish, book_date,book_price, book_star" +
            " from yxq_doubanbook" +
            "<where>" +
            "   <if test='book.bookName != null and book.bookName.length > 0'>" +
            "       and book_name like #{book.bookName}" +
            "   </if>" +
            "   <if test='book.bookAuthor != null and book.bookAuthor.length > 0'>" +
            "       and book_author like #{book.bookAuthor}" +
            "   </if>" +
            "</where>" +
            "<if test='start != null and limit != null'>" +
            "   limit #{start}, #{limit}" +
            "</if>" +
            "</script>")
    public List<Book> selectByWhere(@Param("book") Book book,
                                    @Param("start") Integer start,
                                    @Param("limit") Integer limit);

    @Select("select book_name,book_author,book_publish,book_date,book_price,book_star from yxq_doubanbook where book_name = #{book_name}")
    public Book selectByName(@Param("book_name") String name);


    @Update("UPDATE yxq_doubanbook" +
            "   SET book_name = #{book.bookName}," +
            "       book_author = #{book.bookAuthor}," +
            "       book_publish = #{book.bookPublish}," +
            "       book_date = #{book.bookDate}," +
            "       book_price = #{book.bookPrice}," +
            "       book_star = #{book.bookStar}" +
            " WHERE id = #{book.id}")
    int update(@Param("book") Book book);

    @Select("select book_name, book_author, book_publish, book_date, book_price, book_star from yxq_doubanbook limit #{start}, #{limit}" )
    public List<Book> selectAll(@Param("start") Integer start, @Param("limit") Integer limit);
}



