package com.jaitechltd.bookservice.controller;

import com.jaitechltd.bookservice.model.Book;
import com.jaitechltd.bookservice.service.BookServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/book")
public class BookController {

    private final BookServiceImpl bookServiceImpl;

    public BookController(BookServiceImpl bookServiceImpl) {
        this.bookServiceImpl = bookServiceImpl;
    }

    @PostMapping("/create")
    @Operation(summary = "Create a new book", description = "Create a new book", tags = { "book" },
            operationId = "createBook", responses = { @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200", description = "Book created") })
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        log.info("createBook ...");
        return ResponseEntity.ok(bookServiceImpl.createBook(book));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a book by id", description = "Get a book by id", tags = { "book" },
            operationId = "getBook", responses = { @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200", description = "Book found") })
    public ResponseEntity<Book> getBook(@PathVariable("id") Long id) {
        log.info("getBook ...");
        return ResponseEntity.ok(bookServiceImpl.getBook(id));
    }

    @GetMapping("/all")
    @Operation(summary = "Get all books", description = "Get all books", tags = { "book" },
            operationId = "getAllBooks")
    public ResponseEntity<Iterable<Book>> getAllBooks() {
        log.info("getAllBooks ...");
        return ResponseEntity.ok(bookServiceImpl.getAllBooks());
    }

    @DeleteMapping("/{id}/delete")
    @Operation(summary = "Delete a book by id", description = "Delete a book by id", tags = { "book" },
            operationId = "deleteBook")
    public ResponseEntity<String> deleteBook(@PathVariable("id") Long id) {
        log.info("deleteBook ...");
        bookServiceImpl.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
