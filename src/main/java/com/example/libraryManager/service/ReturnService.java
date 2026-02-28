package com.example.libraryManager.service;

import com.example.libraryManager.dto.EmpruntDto;
import com.example.libraryManager.model.Emprunt;
import com.example.libraryManager.repository.EmpruntRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class ReturnService {

    private final EmpruntRepository empruntRepository;

    public ReturnService(EmpruntRepository empruntRepository) {
        this.empruntRepository = empruntRepository;
    }

    public Emprunt returnBook(Long empruntId){
        Emprunt emprunt = empruntRepository.findById(empruntId).orElse(null);
        if (emprunt != null){
            emprunt.setDateRetourEffective(LocalDate.now());
            emprunt.setStatus("TERMINE");
            double penalite = calculatePenality(emprunt);
            emprunt.setPenalites(penalite);
            emprunt.getBook().setStatus("DISPONIBLE");
            return empruntRepository.save(emprunt);
        }
        return null;
    }

    // Calculer les penalites en considerant le montant a payer par jour de retard est de 500 FCFA
    private double calculatePenality(Emprunt emprunt) {
        if(emprunt.getDateRetourEffective() == null){
            return 0.0;
        }
        long retard = ChronoUnit.DAYS.between(emprunt.getDateRetourEffective(), emprunt.getDateRetourPrevue());
        if (retard > 14){
            return (retard-14) * 500;
        }
        return 0.0;
    }
}
