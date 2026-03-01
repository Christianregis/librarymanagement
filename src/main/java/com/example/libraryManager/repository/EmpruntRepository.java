package com.example.libraryManager.repository;

import com.example.libraryManager.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
    List<Emprunt> searchEmpruntsByDateRetourEffectiveIsNullOrStatusEquals(String status);

    List<Emprunt> searchEmpruntsByStatusContains(String status);
}
