package com.jaitechltd.bookservice.service;

import com.jaitechltd.bookservice.exceptions.BookAlreadyExistsException;
import com.jaitechltd.bookservice.exceptions.BookNotFoundException;
import com.jaitechltd.bookservice.model.Book;
import com.jaitechltd.bookservice.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book createBook(Book book) {

        List<Book> existingBook = bookRepository.findByTitleAndAuthorAndIsbn(book.getTitle(), book.getAuthor(), book.getIsbn());
        log.info("existingBook: {}", existingBook);
        if (!existingBook.isEmpty()) {
            throw new BookAlreadyExistsException("A book with the same title, author, and ISBN already exists, please use a different title, author, or ISBN");
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
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new BookNotFoundException("Book not found with id: " + id);
        }
        return book.get();

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

    @Override
    public Page<Book> getAllBooksByPage(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }
}
