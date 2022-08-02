package br.com.loanapi.controllers;

import br.com.loanapi.mocks.dto.InstallmentDTODataBuilder;
import br.com.loanapi.services.InstallmentService;
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
@DisplayName("Resource: Installment")
@ExtendWith(MockitoExtension.class)
class InstallmentResourceTest {

    @InjectMocks
    InstallmentResource resource;

    @Mock
    InstallmentService service;

    @Test
    @DisplayName("Should test create endpoint")
    void shouldTestCreateEndPoint() {
        Mockito.when(service.create(Mockito.any())).thenReturn(InstallmentDTODataBuilder.builder().build());
        Assertions.assertEquals("<200 OK OK,InstallmentDTO(id=1, maturityDate=11-11-2011, " +
                        "paymentDate=11-11-2021, expired=false, month=4, amortization=1000.0, interest=10.0, " +
                        "value=1100.0, loan=LoanDTO(id=1, startDate=11-11-2011, originalValue=5000.0, " +
                        "debitBalance=2800.0, interestRate=10.0, numberOfInstallments=10, " +
                        "paymentDate=FIFTH_BUSINESS_DAY, amortization=SAC, " +
                        "customer=CustomerDTO(id=1, name=João, lastName=da Silva, birthDate=11-11-2011, " +
                        "signUpDate=11-11-2021, rg=55.626.926-4, cpf=391.534.277-44, email=joao@email.com, " +
                        "pontuation=0.0, address=AddressDTO(id=1, street=Rua 9, neighborhood=Lauzane Paulista, " +
                        "number=583, postalCode=02442-090, city=São Paulo, state=SAO_PAULO, customers=[]), " +
                        "phones=[], loans=[]), installments=[])),[]>",
                resource.create(InstallmentDTODataBuilder.builder().build()).toString());
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
        Mockito.when(service.findById(Mockito.any())).thenReturn(InstallmentDTODataBuilder.builder().build());
        Assertions.assertEquals("<200 OK OK,InstallmentDTO(id=1, maturityDate=11-11-2011, " +
                        "paymentDate=11-11-2021, expired=false, month=4, amortization=1000.0, interest=10.0, " +
                        "value=1100.0, loan=LoanDTO(id=1, startDate=11-11-2011, originalValue=5000.0, " +
                        "debitBalance=2800.0, interestRate=10.0, numberOfInstallments=10, " +
                        "paymentDate=FIFTH_BUSINESS_DAY, amortization=SAC, " +
                        "customer=CustomerDTO(id=1, name=João, lastName=da Silva, birthDate=11-11-2011, " +
                        "signUpDate=11-11-2021, rg=55.626.926-4, cpf=391.534.277-44, email=joao@email.com, " +
                        "pontuation=0.0, address=AddressDTO(id=1, street=Rua 9, neighborhood=Lauzane Paulista, " +
                        "number=583, postalCode=02442-090, city=São Paulo, state=SAO_PAULO, customers=[]), " +
                        "phones=[], loans=[]), installments=[])),[]>",
                resource.findById(1L).toString());
    }

    @Test
    @DisplayName("Should test update")
    void shouldTestUpdate() {
        Mockito.when(service.update(Mockito.any(), Mockito.any())).thenReturn(InstallmentDTODataBuilder.builder().build());
        Assertions.assertEquals("<200 OK OK,InstallmentDTO(id=1, maturityDate=11-11-2011, " +
                        "paymentDate=11-11-2021, expired=false, month=4, amortization=1000.0, interest=10.0, " +
                        "value=1100.0, loan=LoanDTO(id=1, startDate=11-11-2011, originalValue=5000.0, " +
                        "debitBalance=2800.0, interestRate=10.0, numberOfInstallments=10, " +
                        "paymentDate=FIFTH_BUSINESS_DAY, amortization=SAC, " +
                        "customer=CustomerDTO(id=1, name=João, lastName=da Silva, birthDate=11-11-2011, " +
                        "signUpDate=11-11-2021, rg=55.626.926-4, cpf=391.534.277-44, email=joao@email.com, " +
                        "pontuation=0.0, address=AddressDTO(id=1, street=Rua 9, neighborhood=Lauzane Paulista, " +
                        "number=583, postalCode=02442-090, city=São Paulo, state=SAO_PAULO, customers=[]), phones=[], " +
                        "loans=[]), installments=[])),[]>",
                resource.update(InstallmentDTODataBuilder.builder().build(), 1L).toString());
    }

    @Test
    @DisplayName("Should test delete")
    void shouldTestDelete() {
        Mockito.when(service.delete(Mockito.any())).thenReturn(true);
        Assertions.assertEquals("<200 OK OK,true,[]>", resource.delete(1L).toString());
    }

}
