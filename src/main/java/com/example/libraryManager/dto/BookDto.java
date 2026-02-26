package com.example.libraryManager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {
    private String title;
    private String auteur;
    private String isbn;
    private Long categoryId;
    private String status;

    public BookDto(String title, String auteur, String isbn, Long categoryId, String status) {
        this.title = title;
        this.auteur = auteur;
        this.isbn = isbn;
        this.status = status;
        this.categoryId = categoryId;
    }
}
