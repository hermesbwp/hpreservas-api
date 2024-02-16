package com.hp.hpreservasapi.service;

import com.hp.hpreservasapi.exception.DuplicateException;
import com.hp.hpreservasapi.exception.NotFoundException;
import com.hp.hpreservasapi.model.Usuario;
import com.hp.hpreservasapi.repository.IUsuarioRepository;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ActiveProfiles("test")
@AutoConfigureTestDatabase
@Transactional
class UsuarioServiceTest {
    @Mock
    IUsuarioRepository usuarioRepository;
    @Autowired
    public TestEntityManager entityManager;

    
    private EntityManagerFactory entityManagerFactory;
    @Autowired
    @InjectMocks
    UsuarioService usuarioService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        this.entityManager = new TestEntityManager(entityManagerFactory);
        List<Usuario> listOfUsr=new ArrayList<>();
        Usuario usrDb=new Usuario(1L,"Joao Marcos Carlos","joaomarcos@gmail.com","1234568");
        listOfUsr.add(usrDb);
        when(usuarioRepository.findAll().stream()
                .filter(usr-> usr.getNome().equalsIgnoreCase(usrDb.getNome())
                        && usr.getEmail().equalsIgnoreCase(usrDb.getEmail())).toList()).thenReturn(listOfUsr);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usrDb));
        
    }

    @Test
    @DisplayName("Consultando usuario.")
    void get() throws Exception {
        Usuario usrDb=new Usuario(1L,"Joao Marcos Carlos","joaomarcos@gmail.com","1234568");

        assertEquals(usrDb.getNome(),usuarioService.get(1L).getNome());
    }
    @Test
    @DisplayName("Inserindo novo usuario.")
    void add() throws Exception {
        Usuario usrDb = new Usuario(2L,"Victoria Lacerda","vic@gmail.com","1234568");

        usuarioService.add(usrDb);

        verify(usuarioRepository,times(1)).save(usrDb);
    }

    @Test
    @DisplayName("Inserindo usuario duplicado.")
    void addDuplicado() {

        DuplicateException thrown = Assertions.assertThrows(DuplicateException.class, () -> {
            Usuario usrNew = new Usuario(2L,"Joao Marcos Carlos","joaomarcos@gmail.com","1234568");
            usuarioService.add(usrNew);
        });
        assertEquals("Objeto duplicado",thrown.getMessage());
    }

    @Test
    @DisplayName("Modificando usuario existente.")
    void edit() throws Exception {
        Usuario entity = this.entityManager.persist(new Usuario(1L,"Joao Carlos","joao@gmail.com","1234"));
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(entity));
        entity.setNome("Jojo testes");
        usuarioService.edit(entity,1L);
        assertEquals(entity.getNome(),usuarioService.edit(entity,1L).getNome());

    }

    @Test
    @DisplayName("Modificando usuario inexistente.")
    void editUsuarioInexistente() {

        NotFoundException thrown = Assertions.assertThrows(NotFoundException.class, () -> {
            Usuario usrNew = new Usuario(1L,"Joao Marcos Carlos","joaomarcos@gmail.com","1234568");
            usuarioService.edit(usrNew,1L);
        });
        assertEquals("Elemento nao encontrado",thrown.getMessage());
    }

}