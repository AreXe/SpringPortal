package com.arexe.portal.service;

import com.arexe.portal.entity.Book;
import com.arexe.portal.entity.BookStatus;

import java.util.List;

public interface BooksService {

    List<Book> getBookList();
    List<Book> findBooksByTitle(String title);
    Book getBookById(int id);
    void saveBook(Book book);
    void updateBook(int id, Book book);
    void updateBookStatus(int id, String bookStatus);
    void deleteBookById(int id);
}
