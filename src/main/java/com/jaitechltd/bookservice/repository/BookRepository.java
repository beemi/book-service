package com.jaitechltd.bookservice.repository;

import com.jaitechltd.bookservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleAndAuthorAndIsbn(final String title, final String author, final String isbn);
}
