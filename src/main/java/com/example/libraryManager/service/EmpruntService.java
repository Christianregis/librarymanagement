package com.example.libraryManager.service;


import com.example.libraryManager.model.Book;
import com.example.libraryManager.model.Emprunt;
import com.example.libraryManager.model.User;
import com.example.libraryManager.repository.EmpruntRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmpruntService {

    private final EmpruntRepository empruntRepository;

    public EmpruntService(EmpruntRepository empruntRepository) {
        this.empruntRepository = empruntRepository;
    }

    public Emprunt borrowBook(Book book, User user){
        if(!book.isAvailable()){
            throw new RuntimeException("Livre non disponible");
        }
        Emprunt emprunt = new Emprunt();
        emprunt.setBook(book);
        emprunt.setUser(user);
        emprunt.setDateEmprunt(LocalDate.now());
        // On considere ici la la date limite d'emprunt est toujours dans 14 Jours
        emprunt.setDateRetourPrevue(LocalDate.now().plusDays(14));
        emprunt.setStatus("EN_COURS");
        book.setStatus("EMPRUNTE");
        return empruntRepository.save(emprunt);
    }

    public List<Emprunt> getAllEmprunts(){
        return empruntRepository.searchEmpruntsByStatusContains("EN_COURS");
    }

    public Long getEmpruntCount(){
        return (long) empruntRepository.searchEmpruntsByStatusContains("EN_COURS").size();
    }
}
