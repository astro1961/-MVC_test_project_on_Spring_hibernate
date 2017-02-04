package com.astro1961.petProjectOnSpring.dao;

import com.astro1961.petProjectOnSpring.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by astro1961 on 25.01.2017.
 */
@Repository
public class BookDaoImpl implements BookDao {
    private static final Logger logger = LoggerFactory.getLogger(BookDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addBook(Book book) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(book);
        logger.info("Book added book details" + book);
    }

    @Override
    public void updateBook(Book book) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(book);
        logger.info("Book updated details:" + book);
    }

    @Override
    public void removeBook(int id) {
        Session session = this.sessionFactory.getCurrentSession();
            Book book =(Book) session.load(Book.class,new Integer(id));
        if(book!=null){session.delete(book);}
        logger.info("Book deleted details:" + book);
    }

    @Override
    public Book getBookById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Book book = (Book)session.load(Book.class, new Integer(id));
        logger.info("Book loaded" + book);
        return book;
    }

    @Override
    @SuppressWarnings("uncheked")
    public List<Book> listBooks() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Book> bookList = session.createQuery("from Book").list();
        for(Book book:bookList){
            logger.info("Book list" +book);
        }
        return bookList;
    }
}
