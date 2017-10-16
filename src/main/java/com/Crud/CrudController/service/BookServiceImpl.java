package com.Crud.CrudController.service;

import com.Crud.CrudController.dao.BookDao;
import com.Crud.CrudController.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookDao bookDao;

    public void setCrudDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    @Transactional
    public void addBook(Book user) {
        bookDao.addBook(user);
    }
    @Override
    @Transactional
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }
    @Override
    @Transactional
    public void removeBook(int id) {
        bookDao.removeBook(id);
    }
    @Override
    @Transactional
    public Book getBookById(int id) {
        return bookDao.getBookById(id);
    }
    @Override
    @Transactional
    public List<Book> listBooks() {
        return bookDao.listBooks();
    }
}
