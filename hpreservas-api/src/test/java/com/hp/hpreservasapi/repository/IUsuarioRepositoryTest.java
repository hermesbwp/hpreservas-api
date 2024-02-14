package com.hp.hpreservasapi.repository;

import com.hp.hpreservasapi.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class IUsuarioRepositoryTest {
    @Autowired
    IUsuarioRepository repository;
    @Autowired
    TestEntityManager entityManager;

    @Test
    void givenProjectCreated_whenUpdate_thenSuccess() {
        Usuario usrDb=new Usuario(1L,"Joao Marcos Carlos","joaomarcos@gmail.com","1234568");
        entityManager.persist(usrDb);
        repository.save(usrDb);
        String novoNome="Joao Carlos";
        usrDb.setNome(novoNome);
        repository.save(usrDb);
    }

}