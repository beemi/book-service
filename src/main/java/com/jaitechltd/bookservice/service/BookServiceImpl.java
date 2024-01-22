package com.jaitechltd.bookservice.service;

import com.jaitechltd.bookservice.exceptions.BookAlreadyExistsException;
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
    public Book createBook(Book book) throws BookAlreadyExistsException {
        if (bookRepository.findByTitleAndAuthorAndIsbn(book.getTitle(), book.getAuthor(), book.getIsbn()) != null) {
            throw new BookAlreadyExistsException("A book with the same title, author, and ISBN already exists.");
        }

        long currentTimeMillis = System.currentTimeMillis();
        if (book.getCreatedDate() == null) {
            book.setCreatedDate(String.valueOf(currentTimeMillis));
        }
        if (book.getUpdatedDate() == null) {
            book.setUpdatedDate(String.valueOf(currentTimeMillis));
        }
        return bookRepository.save(book);
    }

    @Override
    public Book getBook(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public List<Book> findByTitleAndAuthorAndIsbn(final String title, final String author, final String isbn) {
        return bookRepository.findByTitleAndAuthorAndIsbn(title, author, isbn);
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
