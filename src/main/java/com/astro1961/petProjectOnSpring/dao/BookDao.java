package com.astro1961.petProjectOnSpring.dao;

import com.astro1961.petProjectOnSpring.model.Book;

import java.util.List;

/**
 * Created by astro1961 on 25.01.2017.
 */
public interface BookDao {
    public void addBook(Book book);
    public void updateBook(Book book);
    public void removeBook(int id);
    public Book getBookById(int id);
    public List<Book> listBooks();
}
