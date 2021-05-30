package com.boot.jdbctemplate.dao;

import com.boot.jdbctemplate.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 添加内容
     * @param book
     * @return
     */
    public int addBook(Book book){
        return jdbcTemplate.update("INSERT into book (name,author) values (?,?)",book.getName(),book.getAuthor());
    }

    /**
     * 根据id修改
     * @param book
     * @return
     */
    public int updateBook(Book book){
        return jdbcTemplate.update("update book set name=?,author=? where id=?",book.getName(),book.getAuthor(),book.getId());
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    public int deleteBookById(Integer id){
        return jdbcTemplate.update("delete from book where id=?",id);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public Book getBookById(Integer id){
        return jdbcTemplate.queryForObject("select * from book where id=?",new BeanPropertyRowMapper<>(Book.class),id);
    }

    /**
     * 查询所有
     * @return
     */
    public List<Book> getAllBooks(){
        return jdbcTemplate.query("select * from book",new BeanPropertyRowMapper<>(Book.class));
    }
}
