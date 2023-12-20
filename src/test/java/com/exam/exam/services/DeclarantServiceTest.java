package com.exam.exam.services;

import com.exam.exam.entity.Declarant;
import com.exam.exam.service.DeclarantService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class DeclarantServiceTest {
    @Autowired
    private DeclarantService declarantservice;

    @Test
    void CreateDeclarant() {
        Declarant declarant = new Declarant();
        declarant.setAdresse("adresse");
        declarant.setEmail("email");
        declarant.setTelephone("telephone");
        declarant.setRaisonSociale("raison sociale");
        Declarant declarantSave = declarantservice.create(declarant);
        Assertions.assertNotNull(declarantSave);
    }
}
