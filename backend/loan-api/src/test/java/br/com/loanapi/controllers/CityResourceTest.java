package br.com.loanapi.controllers;

import br.com.loanapi.mocks.dto.CityDTODataBuilder;
import br.com.loanapi.services.CityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
@DisplayName("Resource: City")
@ExtendWith(MockitoExtension.class)
class CityResourceTest {

    @InjectMocks
    CityResource resource;

    @Mock
    CityService service;

    @Test
    @DisplayName("Should test create endpoint")
    void shouldTestCreateEndPoint() {
        Mockito.when(service.create(Mockito.any())).thenReturn(CityDTODataBuilder.builder().build());
        Assertions.assertEquals("<201 CREATED Created,CityDTO(id=1, city=São Paulo, state=SAO_PAULO, " +
                "addresses=null),[]>", resource.create(CityDTODataBuilder.builder().build()).toString());
    }

    @Test
    @DisplayName("Should test find all endpoint")
    void shouldTestFindAll() {
        Mockito.when(service.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertEquals("<200 OK OK,[],[]>", resource.findAll().toString());
    }

    @Test
    @DisplayName("Should test find by id endpoint")
    void shouldTestFindById() {
        Mockito.when(service.findById(Mockito.any())).thenReturn(CityDTODataBuilder.builder().build());
        Assertions.assertEquals("<200 OK OK,CityDTO(id=1, city=São Paulo, state=SAO_PAULO, addresses=null)," +
                "[]>", resource.findById(1L).toString());
    }

    @Test
    @DisplayName("Should test update")
    void shouldTestUpdate() {
        Mockito.when(service.update(Mockito.any(), Mockito.any())).thenReturn(CityDTODataBuilder.builder().build());
        Assertions.assertEquals("<201 CREATED Created,CityDTO(id=1, city=São Paulo, state=SAO_PAULO, " +
                "addresses=null),[]>", resource.update(CityDTODataBuilder.builder().build(),
                1L).toString());
    }

    @Test
    @DisplayName("Should test delete")
    void shouldTestDelete() {
        Mockito.when(service.deleteById(Mockito.any())).thenReturn(true);
        Assertions.assertEquals("<200 OK OK,true,[]>", resource.delete(1L).toString());
    }

}