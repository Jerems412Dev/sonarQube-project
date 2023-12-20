package com.exam.exam.repository;

import com.exam.exam.entity.Declaration;
import com.exam.exam.entity.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    List<Paiement> findByDeclaration(Declaration declaration);
    List<Paiement> findAll();
}
