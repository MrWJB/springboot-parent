package com.boot.jdbctemplate.service;

import com.boot.jdbctemplate.dao.BookDao;
import com.boot.jdbctemplate.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookDao bookDao;

    /**
     * 添加内容
     * @param book
     * @return
     */
    public int addBook(Book book){
        return bookDao.addBook(book);
    }

    /**
     * 根据id修改
     * @param book
     * @return
     */
    public int updateBook(Book book){
        return bookDao.updateBook(book);
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    public int deleteBookById(Integer id){
        return bookDao.deleteBookById(id);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public Book getBookById(Integer id){
        return bookDao.getBookById(id);
    }

    /**
     * 查询所有
     * @return
     */
    public List<Book> getAllBooks(){
        return bookDao.getAllBooks();
    }
}
