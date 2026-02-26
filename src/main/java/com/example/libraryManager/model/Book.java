package com.example.libraryManager.model;

import com.example.libraryManager.dto.BookDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDateTime;
import java.util.List;


@Setter
@Getter
@Entity(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Le titre ne doit pas etre null !")
    @Column(nullable = false)
    @Max(value = 50, message = "La taille du titre ne doit depasser 50")
    private String title;
    private String auteur;

    @NotNull(message = "L'ISBN ne doit pas etre nulle !")
    @UniqueElements(message = "Cet ISBN est deja present pour un autre livre !")
    private String isbn;
    private String status = "DISPONIBLE";
    private LocalDateTime createdAt = LocalDateTime.now();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "book")
    private List<Emprunt> emprunts;

    public BookDto toDto(){
        return new BookDto(getTitle(), getAuteur(), getIsbn(), category.getId());
    }
}
