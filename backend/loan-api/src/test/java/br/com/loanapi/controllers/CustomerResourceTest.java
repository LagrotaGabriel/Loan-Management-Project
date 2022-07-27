package br.com.loanapi.controllers;

import br.com.loanapi.mocks.dto.CustomerDTODataBuilder;
import br.com.loanapi.services.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.ArrayList;

@SpringBootTest
@DisplayName("Resource: Customer")
@ExtendWith(MockitoExtension.class)
class CustomerResourceTest {

    @InjectMocks
    CustomerResource resource;

    @Mock
    CustomerService service;

    @Test
    @DisplayName("Should test create endpoint")
    void shouldTestCreateEndPoint() {
        Mockito.when(service.create(Mockito.any())).thenReturn(CustomerDTODataBuilder.builder().build());
        Assertions.assertEquals("<200 OK OK,CustomerDTO(id=1, name=João, lastName=da Silva, " +
                "birthDate=11-11-2011, signUpDate=11-11-2021, rg=55.626.926-4, cpf=391.534.277-44, " +
                "email=joao@email.com, address=AddressDTO(id=1, street=Rua 9, neighborhood=Lauzane Paulista, " +
                "number=583, postalCode=02442-090, city=São Paulo, state=SAO_PAULO, customers=[]), " +
                "score=ScoreDTO(id=1, pontuation=50.0, customer=null), phones=[], loans=[]),[]>",
                resource.create(CustomerDTODataBuilder.builder()
                .build()).toString());
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
        Mockito.when(service.findById(Mockito.any())).thenReturn(CustomerDTODataBuilder.builder().build());
        Assertions.assertEquals("<200 OK OK,CustomerDTO(id=1, name=João, lastName=da Silva, " +
                "birthDate=11-11-2011, signUpDate=11-11-2021, rg=55.626.926-4, cpf=391.534.277-44, " +
                "email=joao@email.com, address=AddressDTO(id=1, street=Rua 9, neighborhood=Lauzane Paulista, " +
                "number=583, postalCode=02442-090, city=São Paulo, state=SAO_PAULO, customers=[]), " +
                "score=ScoreDTO(id=1, pontuation=50.0, customer=null), phones=[], loans=[]),[]>",
                resource.findById(1L).toString());
    }

    @Test
    @DisplayName("Should test update")
    void shouldTestUpdate() {
        Mockito.when(service.update(Mockito.any(), Mockito.any())).thenReturn(CustomerDTODataBuilder.builder().build());
        Assertions.assertEquals("<200 OK OK,CustomerDTO(id=1, name=João, lastName=da Silva, " +
                "birthDate=11-11-2011, signUpDate=11-11-2021, rg=55.626.926-4, cpf=391.534.277-44, " +
                "email=joao@email.com, address=AddressDTO(id=1, street=Rua 9, neighborhood=Lauzane Paulista, " +
                "number=583, postalCode=02442-090, city=São Paulo, state=SAO_PAULO, customers=[]), " +
                "score=ScoreDTO(id=1, pontuation=50.0, customer=null), phones=[], loans=[]),[]>",
                resource.update(CustomerDTODataBuilder.builder().build(), 1L).toString());
    }

    @Test
    @DisplayName("Should test delete")
    void shouldTestDelete() {
        Mockito.when(service.deleteById(Mockito.any())).thenReturn(true);
        Assertions.assertEquals("<200 OK OK,true,[]>", resource.delete(1L).toString());
    }

}
