package com.Crud.CrudController.dao;

import com.Crud.CrudController.model.Book;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class BookDaoImpl implements BookDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(book);

    }
    @Override
    public void updateBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.update(book);
    }
    @Override
    public void removeBook(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.load(Book.class, id);
        if (book != null) {
            session.delete(book);
        }
    }

    @Override
    public Book getBookById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.load(Book.class, id);
    }
    @Override
    @SuppressWarnings("unchecked")
    public List<Book> listBooks(int first, int max) {
        Session session = sessionFactory.getCurrentSession();
        Query bookList = session.createQuery("FROM Book");
        bookList.setFirstResult(first * max);
        bookList.setMaxResults(max);
        return bookList.list();
    }

    @Override
    public List<Book> getAllBooks() {
        Session session = sessionFactory.getCurrentSession();
        Query bookList = session.createQuery("FROM Book");
        return bookList.list();
    }

    @Override
    public List<Book> searchAllTitles(String title) {
        String newParam = "%" + title + "%";
        Session session = sessionFactory.getCurrentSession();
        Query bookList = session.createQuery("FROM Book WHERE title like '" + newParam +"'");
        return bookList.list();
    }

    @Override
    public List<Book> searchTitles(String title, int first, int max) {
        String newParam = "%" + title + "%";
        Session session = sessionFactory.getCurrentSession();
        Query bookList = session.createQuery("FROM Book WHERE title like '"+ newParam + "'");
        bookList.setFirstResult(first * max);
        bookList.setMaxResults(max);
        return bookList.list();
    }

}
