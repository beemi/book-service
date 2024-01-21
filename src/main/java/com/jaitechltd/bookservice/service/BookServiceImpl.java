package com.jaitechltd.bookservice.service;

import com.jaitechltd.bookservice.model.Book;
import com.jaitechltd.bookservice.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book createBook(Book book) {
        if (book.getCreatedDate() == null) {
            book.setCreatedDate(String.valueOf(System.currentTimeMillis()));
        }
        if (book.getUpdatedDate() == null) {
            book.setUpdatedDate(String.valueOf(System.currentTimeMillis()));
        }
        return bookRepository.save(book);
    }

    @Override
    public Book getBook(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
