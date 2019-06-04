package com.arexe.portal.service;

import com.arexe.portal.entity.Book;
import com.arexe.portal.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("booksService")
@Transactional
public class BooksServiceImpl implements BooksService {

    @Autowired
    BooksRepository booksRepository;

    @Override
    public List<Book> getBookList() {
        return booksRepository.findAll();
    }

    @Override
    public List<Book> findBooksByTitle(String title) {
        return booksRepository.findBooksByTitle(title);
    }

    @Override
    public Book getBookById(int id) {
        return booksRepository.findBookById(id);
    }

    @Override
    public void saveBook(Book book) {
        booksRepository.save(book);
    }

    @Override
    public void updateBook(int id, Book book) {
        Book updateBook = booksRepository.findBookById(id);
        updateBook.setIsbn(book.getIsbn());
        updateBook.setTitle(book.getTitle());
        updateBook.setAuthor(book.getAuthor());
        updateBook.setReleaseDate(book.getReleaseDate());
        updateBook.setDescription(book.getDescription());
        booksRepository.save(updateBook);
    }

    @Override
    public void deleteBookById(int id) {
        booksRepository.deleteBookById(id);
    }
}
