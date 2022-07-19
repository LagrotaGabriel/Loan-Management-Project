package br.com.loanapi.services;

import br.com.loanapi.config.ModelMapperConfig;
import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.mocks.dto.ScoreDTODataBuilder;
import br.com.loanapi.mocks.entity.ScoreEntityDataBuilder;
import br.com.loanapi.models.entities.ScoreEntity;
import br.com.loanapi.repositories.ScoreRepository;
import br.com.loanapi.validations.ScoreValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@DisplayName("Service: Score")
@ExtendWith(MockitoExtension.class)
class ScoreServiceTest {

    @InjectMocks
    ScoreService service;

    @Mock
    ScoreValidation validation;

    @Mock
    ScoreRepository repository;

    @Mock
    ModelMapperConfig modelMapper;

    @Test
    @DisplayName("Should test create method with success")
    void shouldTestCreateMethodWithSuccess() {

        Mockito.when(modelMapper.mapper()).thenReturn(new ModelMapper());
        Mockito.when(validation.validateRequest(Mockito.any())).thenReturn(true);
        Mockito.when(repository.save(Mockito.any())).thenReturn(ScoreEntityDataBuilder.builder().build());

        Assertions.assertEquals("ScoreDTO(id=1, pontuation=50.0, customer=CustomerDTO(id=1, name=João, " +
                        "lastName=da Silva, birthDate=11-11-2011, signUpDate=11-11-2021, rg=55.626.926-4, " +
                        "cpf=391.534.277-44, email=joao@email.com, address=AddressDTO(id=1, street=Rua 9, " +
                        "neighborhood=Lauzane Paulista, number=583, postalCode=02442-090, city=São Paulo, " +
                        "state=SAO_PAULO, customers=null), score=ScoreDTO(id=1, pontuation=50.0, customer=null), " +
                        "phones=null, loans=null))",
                service.create(ScoreDTODataBuilder.builder().build()).toString());

    }

    @Test
    @DisplayName("Should test create method with exception")
    void shouldTestCreateMethodWithException(){

        Mockito.when(validation.validateRequest(Mockito.any())).thenReturn(false);

        try {
            service.create(ScoreDTODataBuilder.builder().build());
            Assertions.fail();
        }
        catch(InvalidRequestException exception) {
            Assertions.assertEquals("Score validation failed", exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should test findAll method with success")
    void shouldTestFindAllMethodWithSuccess() {

        List<ScoreEntity> scores = new ArrayList<>();
        scores.add(ScoreEntityDataBuilder.builder().build());

        Mockito.when(modelMapper.mapper()).thenReturn(new ModelMapper());
        Mockito.when(repository.findAll()).thenReturn(scores);

        Assertions.assertEquals("[ScoreDTO(id=1, pontuation=50.0, customer=CustomerDTO(id=1, name=João, " +
                        "lastName=da Silva, birthDate=11-11-2011, signUpDate=11-11-2021, rg=55.626.926-4, " +
                        "cpf=391.534.277-44, email=joao@email.com, address=AddressDTO(id=1, street=Rua 9, " +
                        "neighborhood=Lauzane Paulista, number=583, postalCode=02442-090, city=São Paulo, " +
                        "state=SAO_PAULO, customers=null), score=ScoreDTO(id=1, pontuation=50.0, customer=null), " +
                        "phones=null, loans=null))]",
                service.findAll().toString());

    }


    @Test
    @DisplayName("Should test findAll method with exception")
    void shouldTestFindAllMethodWithException(){

        List<ScoreEntity> scores = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(scores);

        try {
            service.findAll();
            Assertions.fail();
        }
        catch(ObjectNotFoundException exception) {
            Assertions.assertEquals("There is no Scores saved in the database", exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should test findById method with success")
    void shouldTestFindByIdMethodWithSuccess() {

        Mockito.when(modelMapper.mapper()).thenReturn(new ModelMapper());
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(ScoreEntityDataBuilder.builder().build()));

        Assertions.assertEquals("ScoreDTO(id=1, pontuation=50.0, customer=CustomerDTO(id=1, name=João, " +
                        "lastName=da Silva, birthDate=11-11-2011, signUpDate=11-11-2021, rg=55.626.926-4, " +
                        "cpf=391.534.277-44, email=joao@email.com, address=AddressDTO(id=1, street=Rua 9, " +
                        "neighborhood=Lauzane Paulista, number=583, postalCode=02442-090, city=São Paulo, " +
                        "state=SAO_PAULO, customers=null), score=ScoreDTO(id=1, pontuation=50.0, customer=null), " +
                        "phones=null, loans=null))",
                service.findById(1L).toString());

    }

    @Test
    @DisplayName("Should test findById method with exception")
    void shouldTestFindByIdMethodWithException() {

        Mockito.when(modelMapper.mapper()).thenReturn(new ModelMapper());
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());

        try {
            service.findById(1L);
        }
        catch (ObjectNotFoundException exception){
            Assertions.assertEquals("Score not found", exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should test update method with success")
    void shouldTestUpdateMethodWithSuccess() {

        Mockito.when(modelMapper.mapper()).thenReturn(new ModelMapper());
        Mockito.when(validation.validateRequest(Mockito.any())).thenReturn(true);
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(ScoreEntityDataBuilder.builder().build()));
        Mockito.when(repository.save(Mockito.any())).thenReturn(ScoreEntityDataBuilder.builder().build());

        Assertions.assertEquals("ScoreDTO(id=1, pontuation=50.0, customer=CustomerDTO(id=1, name=João, " +
                        "lastName=da Silva, birthDate=11-11-2011, signUpDate=11-11-2021, rg=55.626.926-4, " +
                        "cpf=391.534.277-44, email=joao@email.com, address=AddressDTO(id=1, street=Rua 9, " +
                        "neighborhood=Lauzane Paulista, number=583, postalCode=02442-090, city=São Paulo, " +
                        "state=SAO_PAULO, customers=null), score=ScoreDTO(id=1, pontuation=50.0, customer=null), " +
                        "phones=null, loans=null))",
                service.update(1L, ScoreDTODataBuilder.builder().build()).toString());

    }

    @Test
    @DisplayName("Should test update method with exception")
    void shouldTestUpdateMethodWithException() {

        Mockito.when(validation.validateRequest(Mockito.any())).thenReturn(false);

        try{
            service.update(1L, ScoreDTODataBuilder.builder().build());
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("Score validation failed", exception.getMessage());
        }
    }

    @Test
    @DisplayName("Should test delete method with success")
    void shouldTestDeleteMethodWithSuccess() {
        Mockito.when(modelMapper.mapper()).thenReturn(new ModelMapper());
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(ScoreEntityDataBuilder.builder().build()));
        Assertions.assertTrue(service.delete(1L));
    }

    @Test
    @DisplayName("Should test delete method with exception")
    void shouldTestDeleteMethodWithException() {
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());

        try{
            service.delete(1L);
            Assertions.fail();
        }
        catch (ObjectNotFoundException exception) {
            Assertions.assertEquals("Score not found", exception.getMessage());
        }

    }

}
