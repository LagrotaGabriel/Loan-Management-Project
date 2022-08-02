package br.com.loanapi.services;

import br.com.loanapi.config.ModelMapperConfig;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.mocks.entity.InstallmentEntityDataBuilder;
import br.com.loanapi.models.entities.InstallmentEntity;
import br.com.loanapi.repositories.InstallmentRepository;
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

@SpringBootTest
@DisplayName("Service: Installment")
@ExtendWith(MockitoExtension.class)
class InstallmentServiceTest {

    @InjectMocks
    InstallmentService service;

    @Mock
    InstallmentRepository repository;

    @Mock
    ModelMapperConfig modelMapper;

    @Test
    @DisplayName("Should test findAll method with success")
    void shouldTestFindAllMethodWithSuccess() {

        List<InstallmentEntity> installments = new ArrayList<>();
        installments.add(InstallmentEntityDataBuilder.builder().build());

        Mockito.when(modelMapper.mapper()).thenReturn(new ModelMapper());
        Mockito.when(repository.findAll()).thenReturn(installments);

        Assertions.assertEquals("[InstallmentDTO(id=1, maturityDate=11-11-2011, paymentDate=11-11-2021, " +
                        "month=4, amortization=1000.0, interest=10.0, value=1100.0, loan=LoanDTO(id=1, " +
                        "startDate=11-11-2011, originalValue=5000.0, debitBalance=2800.0, interestRate=10.0, " +
                        "numberOfInstallments=10, paymentDate=FIFTH_BUSINESS_DAY, amortization=SAC, " +
                        "customer=CustomerDTO(id=1, name=João, lastName=da Silva, " +
                        "birthDate=11-11-2011, signUpDate=11-11-2021, rg=55.626.926-4, cpf=391.534.277-44, " +
                        "email=joao@email.com, pontuation=null, address=AddressDTO(id=1, street=Rua 9, " +
                        "neighborhood=Lauzane Paulista, number=583, postalCode=02442-090, city=São Paulo, " +
                        "state=SAO_PAULO, customers=null), phones=null, loans=null), installments=null))]",
                service.findAll().toString());

    }


    @Test
    @DisplayName("Should test findAll method with exception")
    void shouldTestFindAllMethodWithException(){

        List<InstallmentEntity> cities = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(cities);

        try {
            service.findAll();
            Assertions.fail();
        }
        catch(ObjectNotFoundException exception) {
            Assertions.assertEquals("There is no installments saved in the database", exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should test findById method with success")
    void shouldTestFindByIdMethodWithSuccess() {

        Mockito.when(modelMapper.mapper()).thenReturn(new ModelMapper());
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(InstallmentEntityDataBuilder.builder().build()));

        Assertions.assertEquals("InstallmentDTO(id=1, maturityDate=11-11-2011, paymentDate=11-11-2021, " +
                        "month=4, amortization=1000.0, interest=10.0, value=1100.0, loan=LoanDTO(id=1, " +
                        "startDate=11-11-2011, originalValue=5000.0, debitBalance=2800.0, interestRate=10.0, " +
                        "numberOfInstallments=10, paymentDate=FIFTH_BUSINESS_DAY, amortization=SAC, " +
                        "customer=CustomerDTO(id=1, name=João, lastName=da Silva, " +
                        "birthDate=11-11-2011, signUpDate=11-11-2021, rg=55.626.926-4, cpf=391.534.277-44, " +
                        "email=joao@email.com, pontuation=null, address=AddressDTO(id=1, street=Rua 9, " +
                        "neighborhood=Lauzane Paulista, number=583, postalCode=02442-090, city=São Paulo, " +
                        "state=SAO_PAULO, customers=null), phones=null, loans=null), installments=null))",
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
            Assertions.assertEquals("Installment not found", exception.getMessage());
        }

    }

}
