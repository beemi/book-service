package com.jaitechltd.bookservice.repository;

import com.jaitechltd.bookservice.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleAndAuthorAndIsbn(final String title, final String author, final String isbn);

    @Query(value = "SELECT * FROM book WHERE pages BETWEEN ?1 AND ?2",
            countQuery = "SELECT count(*) FROM book WHERE pages BETWEEN ?1 AND ?2",
            nativeQuery = true)
    Page<Book> findBooksByPageRange(int fromPage, int toPage, Pageable pageable);

    @Query(value = "SELECT * FROM book WHERE price BETWEEN ?1 AND ?2",
            countQuery = "SELECT count(*) FROM book WHERE price BETWEEN ?1 AND ?2",
            nativeQuery = true)
    Page<Book> findBooksByPriceRange(int fromPrice, int toPrice, Pageable pageable);
}
