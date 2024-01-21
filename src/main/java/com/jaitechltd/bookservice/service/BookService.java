package com.jaitechltd.bookservice.service;

import com.jaitechltd.bookservice.model.Book;

import java.util.List;

public interface BookService {

    public Book createBook(Book book);

    Book getBook(Long id);

    List<Book> getAllBooks();

    void deleteBook(Long id);
}
