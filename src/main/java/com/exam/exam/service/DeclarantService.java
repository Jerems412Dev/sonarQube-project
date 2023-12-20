package com.exam.exam.service;
import com.exam.exam.entity.Declarant;
import com.exam.exam.repository.DeclarantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeclarantService {
    private DeclarantRepository repository;

    public DeclarantService(DeclarantRepository repository) {
        this.repository = repository;
    }

    public Declarant create(Declarant declarant) {
        return repository.save(declarant);
    }

    public Declarant getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Declarant> getAll() {
        return repository.findAll();
    }

    public Declarant update(Long id, Declarant updatedDeclarant) {
        if (repository.existsById(id)) {
            updatedDeclarant.setId(id);
            return repository.save(updatedDeclarant);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
