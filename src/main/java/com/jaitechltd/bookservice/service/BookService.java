package com.jaitechltd.bookservice.service;

import com.jaitechltd.bookservice.model.Book;

import java.util.List;

public interface BookService {

    Book createBook(Book book);

    Book getBook(Long id);

    List<Book> findByTitleAndAuthorAndIsbn(final String title, final String author, final String isbn);

    List<Book> getAllBooks();

    void deleteBook(Long id);
}
