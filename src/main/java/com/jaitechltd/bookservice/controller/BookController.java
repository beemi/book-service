package com.jaitechltd.bookservice.controller;

import com.jaitechltd.bookservice.model.Book;
import com.jaitechltd.bookservice.service.BookServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/book")
public class BookController {

    private final BookServiceImpl bookServiceImpl;

    public BookController(BookServiceImpl bookServiceImpl) {
        this.bookServiceImpl = bookServiceImpl;
    }

    @PostMapping("/create")
    @Operation(summary = "Create a new book", description = "Create a new book", tags = {"book"},
            operationId = "createBook", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Book created"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409",
                    description = "Book already exists")})
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        Book createdBook = bookServiceImpl.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a book by id", description = "Get a book by id", tags = {"book"},
            operationId = "getBook", responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200", description = "Book found")})
    public ResponseEntity<?> getBook(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bookServiceImpl.getBook(id));
    }

    @GetMapping("/search")
    @Operation(summary = "Search a book by title, author, and ISBN", description = "Search a book by title, author, and ISBN", tags = {"book"},
            operationId = "searchBook", responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200", description = "List of books found")})
    public ResponseEntity<List<Book>> searchBook(@RequestParam(value = "title", required = false) String title,
                                                 @RequestParam(value = "author", required = false) String author,
                                                 @RequestParam(value = "isbn", required = false) String isbn) {
        log.info("Search a book by title, author, and ISBN request params {}, {}, {} controller ...", title, author, isbn);
        return ResponseEntity.ok(bookServiceImpl.findByTitleAndAuthorAndIsbn(title, author, isbn));
    }

    @GetMapping("/all")
    @Operation(summary = "Get all books", description = "Get all books", tags = {"book"},
            operationId = "getAllBooks")
    public ResponseEntity<Iterable<Book>> getAllBooks() {
        log.info("getAllBooks ...");
        return ResponseEntity.ok(bookServiceImpl.getAllBooks());
    }

    @GetMapping("/allByPage")
    @Operation(summary = "Get all books with pagination", description = "Get all books with pagination", tags = {"book"},
            operationId = "getAllBooksByPage")
    public ResponseEntity<Page<Book>> getAllBooksByPage(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                        @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        log.info("getAllBooksByPage ...");

        if (page < 0 || size <= 1) {
            return ResponseEntity.badRequest().body(Page.empty());
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<Book> books = bookServiceImpl.getAllBooksByPage(pageable);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/count")
    @Operation(summary = "Get count of all books", description = "Get count of all books", tags = {"book"},
            operationId = "getCountOfAllBooks")
    public ResponseEntity<Long> getCountOfAllBooks() {
        log.info("getCountOfAllBooks ...");
        return ResponseEntity.ok(bookServiceImpl.getAllBooks().spliterator().getExactSizeIfKnown());
    }

    @DeleteMapping("/{id}/delete")
    @Operation(summary = "Delete a book by id", description = "Delete a book by id", tags = {"book"},
            operationId = "deleteBook")
    public ResponseEntity<String> deleteBook(@PathVariable("id") Long id) {
        bookServiceImpl.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
