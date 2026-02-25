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
}
