package com.boot.jpa.service;

import com.boot.jpa.dao.BookDao;
import com.boot.jpa.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookDao bookDao;

    public Page<Book> getBookByPage(Pageable pageable){
        return bookDao.findAll(pageable);
    }

    //查询以某个字符开始的所有书。
    public List<Book> getBooksByAuthorStartingWith(String author){
        return bookDao.getBooksByAuthorStartingWith(author);
    }
    //查询单价大于某个值的所有书。
    public List<Book> getBookByPriceGreaterThan(Float price){
        return bookDao.getBookByPriceGreaterThan(price);
    }
    //
    @Query(value = "select * from t_book where id=(select max(id) from t_book )",nativeQuery = true)
    public Book getMaxIdBook(){
        return bookDao.getMaxIdBook();
    }

    @Query(value = "select b from t_book b where b.id > :id and b.author=:author")
    public List<Book> getBookByIdAndAuthor(@Param("author") String author, @Param("id") Integer id){
        return bookDao.getBookByIdAndAuthor(author,id);
    }

    @Query("select b from t_book b where b.id <?2 and b.name like %?1%")
    public List<Book> getBookByIdAndName(String name,Integer id){
        return bookDao.getBookByIdAndName(name,id);
    }

    public void addBook(Book book){
        bookDao.save(book);
    }
}
