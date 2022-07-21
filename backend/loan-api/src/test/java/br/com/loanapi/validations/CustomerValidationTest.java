package br.com.loanapi.validations;

import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.mocks.dto.CustomerDTODataBuilder;
import br.com.loanapi.mocks.dto.PhoneDTODataBuilder;
import br.com.loanapi.mocks.entity.CustomerEntityDataBuilder;
import br.com.loanapi.models.dto.CustomerDTO;
import br.com.loanapi.models.dto.PhoneDTO;
import br.com.loanapi.repositories.CustomerRepository;
import br.com.loanapi.repositories.PhoneRepository;
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
import java.util.List;
import java.util.Optional;

@SpringBootTest
@DisplayName("Validation: Customer")
@ExtendWith(MockitoExtension.class)
class CustomerValidationTest {

    @InjectMocks
    CustomerValidation validation;

    @Mock
    CustomerRepository repository;

    @Mock
    PhoneRepository phoneRepository;

    @Test
    @DisplayName("Should validate name validation with success")
    void shouldValidateNameValidationWithSuccess() {
        Assertions.assertTrue(validation.verifyName(CustomerDTODataBuilder.builder().build().getName()));
    }

    @Test
    @DisplayName("Should validate name validation with exception")
    void shouldValidateNameValidationWithException(){

        try{
            validation.verifyName(CustomerDTODataBuilder.builder().withTooLongName().build().getName());
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("Name validation failed. The name is too long (+65 characters)",
                    exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should validate last name validation with success")
    void shouldValidateLastNameValidationWithSuccess() {
        Assertions.assertTrue(validation.verifyLastName(CustomerDTODataBuilder.builder().build().getLastName()));
    }

    @Test
    @DisplayName("Should validate last name validation with exception")
    void shouldValidateLastNameValidationWithException(){

        try{
            validation.verifyLastName(CustomerDTODataBuilder.builder().withTooLongLastName().build().getLastName());
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("Last name validation failed. The last name is too long (+65 characters)",
                    exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should validate birth date validation with success")
    void shouldValidateBirthDateValidationWithSuccess() {
        Assertions.assertTrue(validation.verifyBirthDate
                (CustomerDTODataBuilder.builder().withRealisticBirthDate().build().getBirthDate()));
    }

    @Test
    @DisplayName("Should validate birth date validation with exception")
    void shouldValidateBirthDateValidationWithException(){

        try{
            validation.verifyBirthDate(CustomerDTODataBuilder.builder().build().getBirthDate());
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("Birth date validation failed. The date pattern is incorrect",
                    exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should validate rg validation with success")
    void shouldValidateRgValidationWithSuccess() {
        Assertions.assertTrue(validation.verifyRg(CustomerDTODataBuilder.builder().build().getRg()));
    }

    @Test
    @DisplayName("Should validate rg validation with exception")
    void shouldValidateRgValidationWithException(){

        try{
            validation.verifyRg(CustomerDTODataBuilder.builder().withInvalidRg().build().getRg());
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("Rg validation failed. The rg pattern is invalid",
                    exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should validate Cpf validation with success")
    void shouldValidateCpfValidationWithSuccess() {
        Assertions.assertTrue(validation.verifyCpf(CustomerDTODataBuilder.builder().build().getCpf()));
    }

    @Test
    @DisplayName("Should validate Cpf validation with exception")
    void shouldValidateCpfValidationWithException(){

        try{
            validation.verifyCpf(CustomerDTODataBuilder.builder().withInvalidCpf().build().getCpf());
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("Cpf validation failed. The cpf pattern is invalid",
                    exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should validate Email validation with success")
    void shouldValidateEmailValidationWithSuccess() {
        Assertions.assertTrue(validation.verifyEmail(CustomerDTODataBuilder.builder().build().getEmail()));
    }

    @Test
    @DisplayName("Should validate Email validation with exception")
    void shouldValidateEmailValidationWithException(){

        try{
            validation.verifyEmail(CustomerDTODataBuilder.builder().withInvalidEmail().build().getEmail());
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("Email validation failed. The email pattern is invalid",
                    exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should validate not null validation with success")
    void shouldValidateNotNullValidationWithSuccess() {
        Assertions.assertTrue(validation.notNull(CustomerDTODataBuilder.builder().build()));
    }

    @Test
    @DisplayName("Should validate not null validation with exception")
    void shouldValidateNotNullValidationWithException() {
        CustomerDTO customer = CustomerDTODataBuilder.builder().build();
        customer.setName(null);

        try{
            validation.notNull(customer);
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("Customer validation failed. Some of the attributes is null or empty.",
                    exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should test exists with success")
    void shouldTestExistsWithSuccess() {

        Mockito.when(repository.findByRg(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(repository.findByCpf(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(repository.findByEmail(Mockito.any())).thenReturn(Optional.empty());

        Assertions.assertTrue(validation.exists(CustomerDTODataBuilder.builder().build(), repository));

    }

    @Test
    @DisplayName("Should test exists with all failures")
    void shouldTestExistsWithAllFailures() {

        Mockito.when(repository.findByRg(Mockito.any())).thenReturn(Optional.of(CustomerEntityDataBuilder.builder().build()));
        Mockito.when(repository.findByCpf(Mockito.any())).thenReturn(Optional.of(CustomerEntityDataBuilder.builder().build()));
        Mockito.when(repository.findByEmail(Mockito.any())).thenReturn(Optional.of(CustomerEntityDataBuilder.builder().build()));

        try {
            validation.exists(CustomerDTODataBuilder.builder().build(), repository);
            Assertions.fail();
        }
        catch (InvalidRequestException exception){
            Assertions.assertEquals("Customer validation failed. Errors: [The typed rg already exists in the " +
                    "database, The typed cpf already exists in the database, The typed email already exists in the " +
                    "database]", exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should test exists with just one failure")
    void shouldTestExistsWithJustOneFailure() {

        Mockito.when(repository.findByRg(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(repository.findByCpf(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(repository.findByEmail(Mockito.any())).thenReturn(Optional.of(CustomerEntityDataBuilder.builder().build()));

        try {
            validation.exists(CustomerDTODataBuilder.builder().build(), repository);
            Assertions.fail();
        }
        catch (InvalidRequestException exception){
            Assertions.assertEquals("Customer validation failed. Error: [The typed email already exists in the " +
                    "database]", exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should validate validate request with success")
    void shouldValidateValidateRequestWithSuccess() {
        Assertions.assertTrue(validation.validateRequest(CustomerDTODataBuilder.builder().withRealisticBirthDate().withPhone().build(), repository, phoneRepository));
    }

    @Test
    @DisplayName("Should validate verifyPhone method with success")
    void shouldValidateVerifyPhoneMethodWithSuccess() {
        List<PhoneDTO> phones = new ArrayList<>();
        phones.add(PhoneDTODataBuilder.builder().build());
        Assertions.assertTrue(validation.verifyPhone(phones, phoneRepository));
    }

    @Test
    @DisplayName("Should validate verifyPhone method with empty list")
    void shouldValidateVerifyPhoneMethodWithEmptyList() {
        List<PhoneDTO> phones = new ArrayList<>();
        try{
            validation.verifyPhone(phones, phoneRepository);
            Assertions.fail();
        }
        catch (InvalidRequestException exception) {
            Assertions.assertEquals("There is no phones sended betwen JSON", exception.getMessage());
        }

    }

}
