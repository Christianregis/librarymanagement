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

    public EmpruntDto(LocalDate dateEmprunt, LocalDate dateRetourPrevue, LocalDate dateRetourEffective,String status){
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
        this.dateRetourEffective = dateRetourEffective;
        this.status = status;
    }
}
