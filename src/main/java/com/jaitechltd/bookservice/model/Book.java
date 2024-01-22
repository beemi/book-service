package com.jaitechltd.bookservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private String category;
    private String language;
    private String edition;

    @Column(length = 1000)
    private String description;
    private String coverPhotoUrl;
    private Double price;
    private Integer pages;
    private Boolean active;
    private String createdDate;
    private String updatedDate;
}
