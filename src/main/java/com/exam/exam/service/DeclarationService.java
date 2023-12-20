package com.exam.exam.service;
import com.exam.exam.entity.Declaration;
import com.exam.exam.repository.DeclarationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeclarationService {
    private DeclarationRepository repository;

    public DeclarationService(DeclarationRepository repository) {
        this.repository = repository;
    }

    public Declaration create(Declaration declaration) {
        return repository.save(declaration);
    }

    public Declaration getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Declaration> getAll() {
        return repository.findAll();
    }

    public Declaration update(Long id, Declaration updatedDeclaration) {
        if (repository.existsById(id)) {
            updatedDeclaration.setId(id);
            return repository.save(updatedDeclaration);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
