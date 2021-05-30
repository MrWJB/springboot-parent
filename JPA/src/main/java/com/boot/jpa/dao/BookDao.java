package com.boot.jpa.dao;

import com.boot.jpa.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 自定义BookDao继承自JpaRepository。
 * JpaRepository 提供了一些基本的数据库操作方法，有基本的增删改查、分页查询、排序查询等。
 */
public interface BookDao extends JpaRepository<Book,Integer> {

    //查询以某个字符开始的所有书。
    List<Book> getBooksByAuthorStartingWith(String author);
    //查询单价大于某个值的所有书。
    List<Book> getBookByPriceGreaterThan(Float price);
    //
    @Query(value = "select * from t_book where id=(select max(id) from t_book )",nativeQuery = true)
    Book getMaxIdBook();

    @Query(value = "select b from t_book b where b.id > :id and b.author=:author")
    List<Book> getBookByIdAndAuthor(@Param("author") String author,@Param("id") Integer id);

    @Query("select b from t_book b where b.id <?2 and b.name like %?1%")
    List<Book> getBookByIdAndName(String name,Integer id);
}
