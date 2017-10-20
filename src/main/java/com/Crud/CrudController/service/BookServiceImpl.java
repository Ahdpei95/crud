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
    public List<Book> listBooks(int first, int max) {
        return bookDao.listBooks(first, max);
    }

    @Override
    @Transactional
    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @Override
    @Transactional
    public List<Book> searchAllTitles(String title) {
        return bookDao.searchAllTitles(title);
    }

    @Override
    @javax.transaction.Transactional
    public List<Book> searchTitles(String title, int first, int max) {
        return bookDao.searchTitles(title, first, max);
    }

}
