package br.com.loanapi.services;

import br.com.loanapi.config.ModelMapperConfig;
import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.mocks.dto.PhoneDTODataBuilder;
import br.com.loanapi.mocks.entity.CustomerEntityDataBuilder;
import br.com.loanapi.mocks.entity.PhoneEntityDataBuilder;
import br.com.loanapi.models.entities.PhoneEntity;
import br.com.loanapi.repositories.CustomerRepository;
import br.com.loanapi.repositories.PhoneRepository;
import br.com.loanapi.validations.PhoneValidation;
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
@DisplayName("Service: Phone")
@ExtendWith(MockitoExtension.class)
class PhoneServiceTest {

    @InjectMocks
    PhoneService service;

    @Mock
    PhoneValidation validation;

    @Mock
    PhoneRepository repository;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    ModelMapperConfig modelMapper;

    @Test
    @DisplayName("Should test create method with success")
    void shouldTestCreateMethodWithSuccess() {

        Mockito.when(modelMapper.mapper()).thenReturn(new ModelMapper());
        Mockito.when(validation.validateRequest(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
        Mockito.when(customerRepository.findById(Mockito.any())).thenReturn(Optional.of(CustomerEntityDataBuilder.builder().withAddresssWithCustomers().withPhoneList().build()));
        Mockito.when(repository.save(Mockito.any())).thenReturn(PhoneEntityDataBuilder.builder().build());

        Assertions.assertEquals("PhoneDTO(id=1, prefix=11, number=97981-5415, phoneType=MOBILE, " +
                        "customerJsonId=null, customer=CustomerDTO(id=1, name=João, lastName=da Silva, " +
                        "birthDate=11-11-2011, signUpDate=11-11-2021, rg=55.626.926-4, cpf=391.534.277-44, " +
                        "email=joao@email.com, pontuation=null, address=AddressDTO(id=1, street=Rua 9, " +
                        "neighborhood=Lauzane Paulista, number=583, postalCode=02442-090, city=São Paulo, " +
                        "state=SAO_PAULO, complement=null, customers=null), phones=null, loans=null))",
                service.create(PhoneDTODataBuilder.builder().withMockedCustomer().build()).toString());

    }

    @Test
    @DisplayName("Should test create method with exception")
    void shouldTestCreateMethodWithException(){

        try {
            service.create(PhoneDTODataBuilder.builder().build());
            Assertions.fail();
        }
        catch(InvalidRequestException exception) {
            Assertions.assertEquals("Customer not found", exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should test findAll method with success")
    void shouldTestFindAllMethodWithSuccess() {

        List<PhoneEntity> phones = new ArrayList<>();
        phones.add(PhoneEntityDataBuilder.builder().build());

        Mockito.when(modelMapper.mapper()).thenReturn(new ModelMapper());
        Mockito.when(repository.findAll()).thenReturn(phones);

        Assertions.assertEquals("[PhoneDTO(id=1, prefix=11, number=97981-5415, phoneType=MOBILE, " +
                        "customerJsonId=null, customer=CustomerDTO(id=1, name=João, lastName=da Silva, " +
                        "birthDate=11-11-2011, signUpDate=11-11-2021, rg=55.626.926-4, cpf=391.534.277-44, " +
                        "email=joao@email.com, pontuation=null, address=AddressDTO(id=1, street=Rua 9, " +
                        "neighborhood=Lauzane Paulista, number=583, postalCode=02442-090, city=São Paulo, " +
                        "state=SAO_PAULO, complement=null, customers=null), phones=null, loans=null))]",
                service.findAll().toString());

    }


    @Test
    @DisplayName("Should test findAll method with exception")
    void shouldTestFindAllMethodWithException(){

        List<PhoneEntity> phones = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(phones);

        try {
            service.findAll();
            Assertions.fail();
        }
        catch(ObjectNotFoundException exception) {
            Assertions.assertEquals("There is no phones saved in the database", exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should test findById method with success")
    void shouldTestFindByIdMethodWithSuccess() {

        Mockito.when(modelMapper.mapper()).thenReturn(new ModelMapper());
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(PhoneEntityDataBuilder.builder().build()));

        Assertions.assertEquals("PhoneDTO(id=1, prefix=11, number=97981-5415, phoneType=MOBILE, " +
                        "customerJsonId=null, customer=CustomerDTO(id=1, name=João, lastName=da Silva, " +
                        "birthDate=11-11-2011, signUpDate=11-11-2021, rg=55.626.926-4, cpf=391.534.277-44, " +
                        "email=joao@email.com, pontuation=null, address=AddressDTO(id=1, street=Rua 9, " +
                        "neighborhood=Lauzane Paulista, number=583, postalCode=02442-090, city=São Paulo, " +
                        "state=SAO_PAULO, complement=null, customers=null), phones=null, loans=null))",
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
            Assertions.assertEquals("Phone not found", exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should test update method with success")
    void shouldTestUpdateMethodWithSuccess() {

        Mockito.when(validation.validateRequest(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
        Mockito.when(customerRepository.findById(Mockito.any())).thenReturn(Optional.of(CustomerEntityDataBuilder.builder().build()));
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(PhoneEntityDataBuilder.builder().build()));
        Mockito.when(repository.save(Mockito.any())).thenReturn(PhoneEntityDataBuilder.builder().build());

        Assertions.assertEquals("PhoneDTO(id=1, prefix=11, number=97981-5415, phoneType=MOBILE, " +
                        "customerJsonId=1, customer=CustomerDTO(id=1, name=João, lastName=da Silva, " +
                        "birthDate=11-11-2011, signUpDate=11-11-2021, rg=55.626.926-4, cpf=391.534.277-44, " +
                        "email=joao@email.com, pontuation=0.0, address=AddressDTO(id=1, street=Rua 9, " +
                        "neighborhood=Lauzane Paulista, number=583, postalCode=02442-090, city=São Paulo, " +
                        "state=SAO_PAULO, complement=null, customers=[]), phones=[], loans=[]))",
                service.update(1L, PhoneDTODataBuilder.builder().build()).toString());

    }

    @Test
    @DisplayName("Should test update method with exception")
    void shouldTestUpdateMethodWithException() {

        try{
            service.update(1L, PhoneDTODataBuilder.builder().build());
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("Customer not found", exception.getMessage());
        }
    }

    @Test
    @DisplayName("Should test delete method with success")
    void shouldTestDeleteMethodWithSuccess() {
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(PhoneEntityDataBuilder.builder().build()));
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
        catch (InvalidRequestException exception) {
            Assertions.assertEquals("Phone not found", exception.getMessage());
        }

    }

}
