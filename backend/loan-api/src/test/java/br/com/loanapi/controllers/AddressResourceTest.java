package br.com.loanapi.controllers;

import br.com.loanapi.mocks.dto.AddressDTODataBuilder;
import br.com.loanapi.services.AddressService;
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
@DisplayName("Resource: Address")
@ExtendWith(MockitoExtension.class)
class AddressResourceTest {

    @InjectMocks
    AddressResource resource;

    @Mock
    AddressService service;

    @Test
    @DisplayName("Should test create endpoint")
    void shouldTestCreateEndPoint() {
        Mockito.when(service.create(Mockito.any())).thenReturn(AddressDTODataBuilder.builder().build());
        Assertions.assertEquals("<200 OK OK,AddressDTO(id=1, street=Rua 9, neighborhood=Lauzane Paulista, " +
                "number=583, postalCode=02442-090, city=São Paulo, state=SAO_PAULO, customers=[]),[]>",
                resource.create(AddressDTODataBuilder.builder().build()).toString());
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
        Mockito.when(service.findById(Mockito.any())).thenReturn(AddressDTODataBuilder.builder().build());
        Assertions.assertEquals("<200 OK OK,AddressDTO(id=1, street=Rua 9, neighborhood=Lauzane Paulista, " +
                "number=583, postalCode=02442-090, city=São Paulo, state=SAO_PAULO, customers=[]),[]>",
                resource.findById(1L).toString());
    }

    @Test
    @DisplayName("Should test update")
    void shouldTestUpdate() {
        Mockito.when(service.update(Mockito.any(), Mockito.any())).thenReturn(AddressDTODataBuilder.builder().build());
        Assertions.assertEquals("<200 OK OK,AddressDTO(id=1, street=Rua 9, neighborhood=Lauzane Paulista, " +
                "number=583, postalCode=02442-090, city=São Paulo, state=SAO_PAULO, customers=[]),[]>",
                resource.update(AddressDTODataBuilder.builder().build(), 1L).toString());
    }

    @Test
    @DisplayName("Should test delete")
    void shouldTestDelete() {
        Mockito.when(service.deleteById(Mockito.any())).thenReturn(true);
        Assertions.assertEquals("<200 OK OK,true,[]>", resource.delete(1L).toString());
    }

}
