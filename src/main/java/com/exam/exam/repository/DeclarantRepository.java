package com.exam.exam.repository;

import com.exam.exam.entity.Declarant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeclarantRepository extends JpaRepository<Declarant, Long> {
    List<Declarant> findAll();
}
