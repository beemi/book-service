package com.jaitechltd.bookservice.controller;

import com.jaitechltd.bookservice.model.Book;
import com.jaitechltd.bookservice.service.BookServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/book")
public class BookController {

    private final BookServiceImpl bookServiceImpl;

    public BookController(BookServiceImpl bookServiceImpl) {
        this.bookServiceImpl = bookServiceImpl;
    }

    @RequestMapping("/create")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        log.info("createBook ...");
        return ResponseEntity.ok(bookServiceImpl.createBook(book));
    }

    @RequestMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") Long id) {
        log.info("getBook ...");
        return ResponseEntity.ok(bookServiceImpl.getBook(id));
    }

    @RequestMapping("/all")
    public ResponseEntity<Iterable<Book>> getAllBooks() {
        log.info("getAllBooks ...");
        return ResponseEntity.ok(bookServiceImpl.getAllBooks());
    }
}
