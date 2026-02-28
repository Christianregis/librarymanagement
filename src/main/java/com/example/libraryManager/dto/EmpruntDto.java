package com.example.libraryManager.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmpruntDto {
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourEffective;
    private String status;
    private Long bookId;
    private Long userId;
    private Double penalites;

    public EmpruntDto(LocalDate dateEmprunt, LocalDate dateRetourPrevue, LocalDate dateRetourEffective,String status, Long bookId, Long userId, Double penalites){
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
        this.dateRetourEffective = dateRetourEffective;
        this.status = status;
        this.bookId = bookId;
        this.userId = userId;
        this.penalites =penalites;
    }
}
