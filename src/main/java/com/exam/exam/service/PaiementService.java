package com.exam.exam.service;
import com.exam.exam.entity.Paiement;
import com.exam.exam.repository.PaiementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaiementService {
    private PaiementRepository repository;

    public PaiementService(PaiementRepository repository) {
        this.repository = repository;
    }

    public Paiement create(Paiement paiement) {
        return repository.save(paiement);
    }

    public Paiement getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Paiement> getAll() {
        return repository.findAll();
    }

    public Paiement update(Long id, Paiement updatedPaiement) {
        if (repository.existsById(id)) {
            updatedPaiement.setId(id);
            return repository.save(updatedPaiement);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
