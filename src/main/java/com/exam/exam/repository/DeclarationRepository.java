package com.exam.exam.repository;

import com.exam.exam.entity.Declarant;
import com.exam.exam.entity.Declaration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeclarationRepository extends JpaRepository<Declaration, Long> {
    List<Declaration> findByDeclarant(Declarant declarant);
    List<Declaration> findAll();
}
