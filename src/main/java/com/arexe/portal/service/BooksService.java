package com.arexe.portal.service;

import com.arexe.portal.entity.Book;

import java.util.List;

public interface BooksService {

    List<Book> getBookList();
    List<Book> findBooksByTitle(String title);
    Book getBookById(int id);
    void saveBook(Book book);
    void updateBook(int id, Book book);
    void deleteBookById(int id);
}
