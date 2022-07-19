package br.com.loanapi.services;

import br.com.loanapi.config.ModelMapperConfig;
import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.mocks.dto.AddressDTODataBuilder;
import br.com.loanapi.mocks.entity.AddressEntityDataBuilder;
import br.com.loanapi.models.entities.AddressEntity;
import br.com.loanapi.repositories.AddressRepository;
import br.com.loanapi.validations.AddressValidation;
import lombok.extern.slf4j.Slf4j;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.loanapi.utils.StringConstants.LOG_BAR;

@Slf4j
@SpringBootTest
@DisplayName("Service: Address")
@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @InjectMocks AddressService service;

    @Mock AddressValidation validation;
    @Mock AddressRepository repository;
    @Mock ModelMapperConfig modelMapper;

    @Test
    @DisplayName("Should test create method with success")
    void shouldTestCreateMethodWithSuccess(){

        log.info(LOG_BAR);
        log.info("[PROGRESS] Running create method test with success...");

        Mockito.when(validation.validateRequest(Mockito.any(), Mockito.any())).thenReturn(true);
        Mockito.when(modelMapper.mapper()).thenReturn(new ModelMapper());
        Mockito.when(repository.save(Mockito.any())).thenReturn(AddressEntityDataBuilder.builder().build());

        Assertions.assertEquals("AddressDTO(id=1, street=Rua 9, neighborhood=Lauzane Paulista, number=583, " +
                "postalCode=02442-090, city=São Paulo, state=SAO_PAULO, customers=null)",
                service.create(AddressDTODataBuilder.builder().build()).toString());

    }

    @Test
    @DisplayName("Should test create method with exception")
    void shouldTestCreateMethodWithException() {

        log.info(LOG_BAR);
        log.info("[PROGRESS] Running create method test with exception...");

        Mockito.when(validation.validateRequest(Mockito.any(), Mockito.any())).thenReturn(false);

        try{
            service.create(AddressDTODataBuilder.builder().build());
            Assertions.fail();
        }
        catch (InvalidRequestException exception){
            Assertions.assertEquals("Address validation failed", exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should test find all method with success")
    void shouldTestFindAllMethodWithSuccess() {

        log.info(LOG_BAR);
        log.info("[PROGRESS] Running findAll method test with success...");

        List<AddressEntity> addressEntityList = new ArrayList<>();
        addressEntityList.add(AddressEntityDataBuilder.builder().build());

        Mockito.when(repository.findAll()).thenReturn(addressEntityList);
        Mockito.when(modelMapper.mapper()).thenReturn(new ModelMapper());

        Assertions.assertEquals("[AddressDTO(id=1, street=Rua 9, neighborhood=Lauzane Paulista, number=583, " +
                "postalCode=02442-090, city=São Paulo, state=SAO_PAULO, customers=null)]", service.findAll().toString());

    }

    @Test
    @DisplayName("Should test find all method with exception")
    void shouldTestFindAllMethodWithException() {

        log.info(LOG_BAR);
        log.info("[PROGRESS] Running findAll method test with exception...");

        List<AddressEntity> addressEntityList = new ArrayList<>();

        Mockito.when(repository.findAll()).thenReturn(addressEntityList);

        try{
            service.findAll();
            Assertions.fail();
        }
        catch (ObjectNotFoundException exception) {
            Assertions.assertEquals("There is no addresses saved in the database", exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should test find by id method with success")
    void shouldTestFindByIdMethodWithSuccess() {

        log.info(LOG_BAR);
        log.info("[PROGRESS] Running findById method test with success...");

        Mockito.when(modelMapper.mapper()).thenReturn(new ModelMapper());
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(AddressEntityDataBuilder.builder().build()));

        Assertions.assertEquals("AddressDTO(id=1, street=Rua 9, neighborhood=Lauzane Paulista, number=583, " +
                        "postalCode=02442-090, city=São Paulo, state=SAO_PAULO, customers=null)",
                service.findById(1L).toString());

    }

    @Test
    @DisplayName("Should test find by id method with exception")
    void shouldTestFindByIdMethodWithException() {

        log.info(LOG_BAR);
        log.info("[PROGRESS] Running findById method test with exception...");

        Mockito.when(modelMapper.mapper()).thenReturn(new ModelMapper());
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());

        try{
            service.findById(1L);
            Assertions.fail();
        }
        catch (ObjectNotFoundException exception) {
            Assertions.assertEquals("Address not found", exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should test update method with success")
    void shouldTestUpdateMethodWithSuccess() {

        log.info(LOG_BAR);
        log.info("[PROGRESS] Running update method test with success...");

        Mockito.when(validation.validateRequest(Mockito.any(), Mockito.any())).thenReturn(true);
        Mockito.when(modelMapper.mapper()).thenReturn(new ModelMapper());
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(AddressEntityDataBuilder.builder().build()));
        Mockito.when(repository.save(Mockito.any())).thenReturn(AddressEntityDataBuilder.builder().build());

        Assertions.assertEquals("AddressDTO(id=1, street=Rua 9, neighborhood=Lauzane Paulista, number=583, " +
                "postalCode=02442-090, city=São Paulo, state=SAO_PAULO, customers=null)",
                service.update(1L, AddressDTODataBuilder.builder().build()).toString());

    }

    @Test
    @DisplayName("Should test update method with validation exception")
    void shouldTestUpdateMethodWithValidationException() {

        log.info(LOG_BAR);
        log.info("[PROGRESS] Running update method test with validation exception...");

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(AddressEntityDataBuilder.builder().build()));
        Mockito.when(validation.validateRequest(Mockito.any(), Mockito.any())).thenReturn(false);

        try {
            service.update(1L, AddressDTODataBuilder.builder().build());
            Assertions.fail();
        }
        catch (InvalidRequestException exception) {
            Assertions.assertEquals("Address validation failed", exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should test update method with not found exception")
    void shouldTestUpdateMethodWithNotFoundException() {

        log.info(LOG_BAR);
        log.info("[PROGRESS] Running update method test with not found exception...");

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());

        try {
            service.update(1L, AddressDTODataBuilder.builder().build());
            Assertions.fail();
        }
        catch (ObjectNotFoundException exception) {
            Assertions.assertEquals("Address not found", exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should test delete by id method with success")
    void shouldTestDeleteByIdMethodWithSuccess() {
        log.info(LOG_BAR);
        log.info("[PROGRESS] Running deleteById method test with success...");
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(AddressEntityDataBuilder.builder().build()));
        Assertions.assertTrue(service.deleteById(1L));
    }

    @Test
    @DisplayName("Should test delete by id method with exception")
    void shouldTestDeleteByIdMethodWithException() {
        log.info(LOG_BAR);
        log.info("[PROGRESS] Running deleteById method test with exception...");
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());
        try {
            service.deleteById(1L);
            Assertions.fail();
        }
        catch (ObjectNotFoundException exception) {
            Assertions.assertEquals("Address not found", exception.getMessage());
        }
    }

}
