package br.com.loanapi.services;

import br.com.loanapi.config.ModelMapperConfig;
import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.mocks.dto.CityDTODataBuilder;
import br.com.loanapi.mocks.entity.CityEntityDataBuilder;
import br.com.loanapi.models.entities.CityEntity;
import br.com.loanapi.repositories.CityRepository;
import br.com.loanapi.validations.CityValidation;
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

import static br.com.loanapi.utils.StringConstants.CITY_NOT_FOUND;

@SpringBootTest
@DisplayName("Service: City")
@ExtendWith(MockitoExtension.class)
class CityServiceTest {

    @InjectMocks
    CityService service;

    @Mock
    CityValidation cityValidation;

    @Mock
    CityRepository repository;

    @Mock
    ModelMapperConfig modelMapper;

    @Test
    @DisplayName("Should test create method with success")
    void shouldTestCreateMethodWithSuccess(){

        Mockito.when(modelMapper.mapper()).thenReturn(new ModelMapper());
        Mockito.when(cityValidation.validateRequest(Mockito.any(), Mockito.any())).thenReturn(true);
        Mockito.when(repository.save(Mockito.any())).thenReturn(CityEntityDataBuilder.builder().build());

        Assertions.assertEquals("CityDTO(id=1, city=São Paulo, state=SAO_PAULO, addresses=null)",
                service.create(CityDTODataBuilder.builder().build()).toString());

    }

    @Test
    @DisplayName("Should test create method with exception")
    void shouldTestCreateMethodWithException(){

        Mockito.when(cityValidation.validateRequest(Mockito.any(), Mockito.any())).thenReturn(false);

        try {
            service.create(CityDTODataBuilder.builder().build());
            Assertions.fail();
        }
        catch(InvalidRequestException exception) {
            Assertions.assertEquals("City validation failed", exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should test findAll method with success")
    void shouldTestFindAllMethodWithSuccess(){

        List<CityEntity> cities = new ArrayList<>();
        cities.add(CityEntityDataBuilder.builder().build());

        Mockito.when(modelMapper.mapper()).thenReturn(new ModelMapper());
        Mockito.when(repository.findAll()).thenReturn(cities);

        Assertions.assertEquals("[CityDTO(id=1, city=São Paulo, state=SAO_PAULO, addresses=null)]",
                service.findAll().toString());

    }


    @Test
    @DisplayName("Should test findAll method with exception")
    void shouldTestFindAllMethodWithException(){

        List<CityEntity> cities = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(cities);

        try {
            service.findAll();
            Assertions.fail();
        }
        catch(ObjectNotFoundException exception) {
            Assertions.assertEquals("There is no cities saved in the database", exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should test findById method with success")
    void shouldTestFindByIdMethodWithSuccess() {

        Mockito.when(modelMapper.mapper()).thenReturn(new ModelMapper());
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(CityEntityDataBuilder.builder().build()));

        Assertions.assertEquals("CityDTO(id=1, city=São Paulo, state=SAO_PAULO, addresses=null)",
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
            Assertions.assertEquals("City not found", exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should test update method with success")
    void shouldTestUpdateMethodWithSuccess() {

        Mockito.when(modelMapper.mapper()).thenReturn(new ModelMapper());
        Mockito.when(cityValidation.validateRequest(Mockito.any(), Mockito.any())).thenReturn(true);
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(CityEntityDataBuilder.builder().withAddress().build()));
        Mockito.when(repository.save(Mockito.any())).thenReturn(CityEntityDataBuilder.builder().build());

        Assertions.assertEquals("CityDTO(id=1, city=São Paulo, state=SAO_PAULO, addresses=null)",
                service.update(1L, CityDTODataBuilder.builder().build()).toString());

    }

    @Test
    @DisplayName("Should test update method with exception")
    void shouldTestUpdateMethodWithException() {

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(CityEntityDataBuilder.builder().build()));
        Mockito.when(cityValidation.validateRequest(Mockito.any(), Mockito.any())).thenReturn(false);

        try{
            service.update(1L, CityDTODataBuilder.builder().build());
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("City validation failed", exception.getMessage());
        }
    }

    @Test
    @DisplayName("Should test update method with city not found")
    void shouldTestUpdateMethodWithCityNotFound() {

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());

        try{
            service.update(1L, CityDTODataBuilder.builder().build());
            Assertions.fail();
        }
        catch(ObjectNotFoundException exception){
            Assertions.assertEquals(CITY_NOT_FOUND, exception.getMessage());
        }
    }

    @Test
    @DisplayName("Should test delete method with success")
    void shouldTestDeleteMethodWithSuccess() {
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(CityEntityDataBuilder.builder().build()));
        Assertions.assertTrue(service.deleteById(1L));
    }

    @Test
    @DisplayName("Should test delete method with exception")
    void shouldTestDeleteMethodWithException() {
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());

        try{
            service.deleteById(1L);
            Assertions.fail();
        }
        catch (ObjectNotFoundException exception) {
            Assertions.assertEquals("City not found", exception.getMessage());
        }

    }


}
