package com.Crud.CrudController.dao;

import com.Crud.CrudController.model.Book;

import java.util.List;

public interface BookDao {
    void addBook(Book book);
    void updateBook(Book book);
    void removeBook(int id);
    Book getBookById(int id);
    List<Book> listBooks(int first, int max);
    List<Book> getAllBooks();
    List<Book> searchAllTitles(String title);
    List<Book> searchTitles(String title, int first, int max);
}
