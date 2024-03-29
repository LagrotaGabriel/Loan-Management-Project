package br.com.loanapi.services;

import br.com.loanapi.config.ModelMapperConfig;
import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.mocks.dto.InstallmentDTODataBuilder;
import br.com.loanapi.mocks.dto.LoanDTODataBuilder;
import br.com.loanapi.mocks.entity.CustomerEntityDataBuilder;
import br.com.loanapi.mocks.entity.LoanEntityDataBuilder;
import br.com.loanapi.models.dto.InstallmentDTO;
import br.com.loanapi.models.entities.LoanEntity;
import br.com.loanapi.proxys.InstallmentServiceProxy;
import br.com.loanapi.repositories.CustomerRepository;
import br.com.loanapi.repositories.LoanRepository;
import br.com.loanapi.validations.LoanValidation;
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
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@DisplayName("Service: Loan")
@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    @InjectMocks
    LoanService service;

    @Mock
    LoanValidation validation;

    @Mock
    LoanRepository repository;

    @Mock
    InstallmentServiceProxy proxy;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    ModelMapperConfig modelMapper;

    @Test
    @DisplayName("Should test create method with success")
    void shouldTestCreateMethodWithSuccess() {

        List<InstallmentDTO> installmentDTOList = new ArrayList<>();
        installmentDTOList.add(InstallmentDTODataBuilder.builder().build());

        Mockito.when(modelMapper.mapper()).thenReturn(new ModelMapper());
        Mockito.when(validation.validateRequest(Mockito.any())).thenReturn(true);
        Mockito.when(proxy.calculateInstallments(Mockito.any())).thenReturn(ResponseEntity.ok().body(installmentDTOList));
        Mockito.when(customerRepository.findById(Mockito.any())).thenReturn(Optional.of(CustomerEntityDataBuilder.builder().withLoanList().build()));

        Assertions.assertEquals("LoanDTO(id=1, startDate=11-11-2011, originalValue=5000.0, " +
                        "debitBalance=5000.0, interestRate=10.0, numberOfInstallments=10, " +
                        "paymentDate=LAST_BUSINESS_DAY, amortization=SAC, customer=CustomerDTO(id=1, name=João, " +
                        "lastName=da Silva, birthDate=11-11-2011, signUpDate=11-11-2021, rg=55.626.926-4, " +
                        "cpf=391.534.277-44, email=joao@email.com, pontuation=0.0, address=AddressDTO(id=1, " +
                        "street=Rua 9, neighborhood=Lauzane Paulista, number=583, postalCode=02442-090, " +
                        "city=São Paulo, state=SAO_PAULO, complement=null, customers=[]), phones=[], loans=[]), " +
                        "installments=[InstallmentDTO(id=1, createdDate=null, maturityDate=11-11-2011, " +
                        "paymentDate=11-11-2021, month=4, amortization=1000.0, interest=10.0, value=1100.0, " +
                        "notes=null, loan=LoanDTO(id=1, startDate=11-11-2011, originalValue=5000.0, " +
                        "debitBalance=2800.0, interestRate=10.0, numberOfInstallments=10, " +
                        "paymentDate=LAST_BUSINESS_DAY, amortization=SAC, customer=CustomerDTO(id=1, name=João, " +
                        "lastName=da Silva, birthDate=11-11-2011, signUpDate=11-11-2021, rg=55.626.926-4, " +
                        "cpf=391.534.277-44, email=joao@email.com, pontuation=0.0, address=AddressDTO(id=1, " +
                        "street=Rua 9, neighborhood=Lauzane Paulista, number=583, postalCode=02442-090, " +
                        "city=São Paulo, state=SAO_PAULO, complement=null, customers=[]), phones=[], loans=[]), " +
                        "installments=[]))])",
                service.create(1L, LoanDTODataBuilder.builder().build()).toString());

    }

    @Test
    @DisplayName("Should test create method with exception")
    void shouldTestCreateMethodWithException(){

        Mockito.when(validation.validateRequest(Mockito.any())).thenReturn(false);
        Mockito.when(customerRepository.findById(Mockito.any())).thenReturn(Optional.of(CustomerEntityDataBuilder.builder().build()));

        try {
            service.create(1L, LoanDTODataBuilder.builder().build());
            Assertions.fail();
        }
        catch(InvalidRequestException exception) {
            Assertions.assertEquals("Customer not found", exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should test findAll method with success")
    void shouldTestFindAllMethodWithSuccess() {

        List<LoanEntity> loans = new ArrayList<>();
        loans.add(LoanEntityDataBuilder.builder().build());

        Mockito.when(modelMapper.mapper()).thenReturn(new ModelMapper());
        Mockito.when(repository.findAll()).thenReturn(loans);

        Assertions.assertEquals("[LoanDTO(id=1, startDate=11-11-2011, originalValue=5000.0, " +
                        "debitBalance=2800.0, interestRate=10.0, numberOfInstallments=10, " +
                        "paymentDate=LAST_BUSINESS_DAY, amortization=SAC, customer=CustomerDTO(id=1, name=João, " +
                        "lastName=da Silva, birthDate=11-11-2011, signUpDate=11-11-2021, rg=55.626.926-4, " +
                        "cpf=391.534.277-44, email=joao@email.com, pontuation=null, address=AddressDTO(id=1, " +
                        "street=Rua 9, neighborhood=Lauzane Paulista, number=583, postalCode=02442-090, " +
                        "city=São Paulo, state=SAO_PAULO, complement=null, customers=null), phones=null, " +
                        "loans=null), installments=null)]",
                service.findAll().toString());

    }


    @Test
    @DisplayName("Should test findAll method with exception")
    void shouldTestFindAllMethodWithException(){

        List<LoanEntity> loans = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(loans);

        try {
            service.findAll();
            Assertions.fail();
        }
        catch(ObjectNotFoundException exception) {
            Assertions.assertEquals("There is no loans saved in the database", exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should test findById method with success")
    void shouldTestFindByIdMethodWithSuccess() {

        Mockito.when(modelMapper.mapper()).thenReturn(new ModelMapper());
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(LoanEntityDataBuilder.builder().build()));

        Assertions.assertEquals("LoanDTO(id=1, startDate=11-11-2011, originalValue=5000.0, " +
                        "debitBalance=2800.0, interestRate=10.0, numberOfInstallments=10, " +
                        "paymentDate=LAST_BUSINESS_DAY, amortization=SAC, customer=CustomerDTO(id=1, name=João, " +
                        "lastName=da Silva, birthDate=11-11-2011, signUpDate=11-11-2021, rg=55.626.926-4, " +
                        "cpf=391.534.277-44, email=joao@email.com, pontuation=null, address=AddressDTO(id=1, " +
                        "street=Rua 9, neighborhood=Lauzane Paulista, number=583, postalCode=02442-090, " +
                        "city=São Paulo, state=SAO_PAULO, complement=null, customers=null), phones=null, " +
                        "loans=null), installments=null)",
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
            Assertions.assertEquals("Loan not found", exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should test delete method with success")
    void shouldTestDeleteMethodWithSuccess() {
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(LoanEntityDataBuilder.builder().build()));
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
            Assertions.assertEquals("Loan not found", exception.getMessage());
        }

    }

}