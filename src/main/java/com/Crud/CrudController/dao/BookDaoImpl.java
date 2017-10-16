package com.Crud.CrudController.dao;

import com.Crud.CrudController.model.Book;
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
        Book book = session.load(Book.class, new Integer(id));
        if (book != null) {
            session.delete(book);
        }
    }
    @Override
    public Book getBookById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.load(Book.class, new Integer(id));
    }
    @Override
    @SuppressWarnings("unchecked")
    public List<Book> listBooks() {
        /*Session session = sessionFactory.getCurrentSession();
        Query bookList = session.createQuery("FROM Book");
        List<Book> books = bookList.list();
        *//*for (Book book :
                books) {
            System.out.println(book.getId());
        }*//*
        return books;*/
     return sessionFactory.getCurrentSession().createNativeQuery("SELECT * FROM book").addEntity(Book.class).list();
    }

}
