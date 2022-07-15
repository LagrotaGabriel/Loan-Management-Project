package br.com.loanapi.validations;

import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.mocks.dto.AddressDTODataBuilder;
import br.com.loanapi.mocks.dto.CustomerDTODataBuilder;
import br.com.loanapi.mocks.entity.CustomerEntityDataBuilder;
import br.com.loanapi.models.dto.AddressDTO;
import br.com.loanapi.models.dto.CustomerDTO;
import br.com.loanapi.repositories.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.Optional;

@SpringBootTest(classes = CustomerValidation.class)
@DisplayName("Validation: Customer")
class CustomerValidationTest {

    @InjectMocks
    CustomerValidation validation;

    @Mock
    CustomerRepository repository;

    @Test
    @DisplayName("Should validate name validation with success")
    void shouldValidateNameValidationWithSuccess() throws ParseException {
        Assertions.assertTrue(validation.verifyName(CustomerDTODataBuilder.builder().build().getName()));
    }

    @Test
    @DisplayName("Should validate name validation with exception")
    void shouldValidateNameValidationWithException(){

        try{
            validation.verifyName(CustomerDTODataBuilder.builder().withTooLongName().build().getName());
            Assertions.fail();
        }
        catch(InvalidRequestException | ParseException exception){
            Assertions.assertEquals("Name validation failed. The name is too long (+65 characters)",
                    exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should validate last name validation with success")
    void shouldValidateLastNameValidationWithSuccess() throws ParseException {
        Assertions.assertTrue(validation.verifyLastName(CustomerDTODataBuilder.builder().build().getLastName()));
    }

    @Test
    @DisplayName("Should validate last name validation with exception")
    void shouldValidateLastNameValidationWithException(){

        try{
            validation.verifyLastName(CustomerDTODataBuilder.builder().withTooLongLastName().build().getLastName());
            Assertions.fail();
        }
        catch(InvalidRequestException | ParseException exception){
            Assertions.assertEquals("Last name validation failed. The last name is too long (+65 characters)",
                    exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should validate birth date validation with success")
    void shouldValidateBirthDateValidationWithSuccess() throws ParseException {
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
        catch(InvalidRequestException | ParseException exception){
            Assertions.assertEquals("Birth date validation failed. The year must be between 1900 and 2008",
                    exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should validate rg validation with success")
    void shouldValidateRgValidationWithSuccess() throws ParseException {
        Assertions.assertTrue(validation.verifyRg(CustomerDTODataBuilder.builder().build().getRg()));
    }

    @Test
    @DisplayName("Should validate rg validation with exception")
    void shouldValidateRgValidationWithException(){

        try{
            validation.verifyRg(CustomerDTODataBuilder.builder().withInvalidRg().build().getRg());
            Assertions.fail();
        }
        catch(InvalidRequestException | ParseException exception){
            Assertions.assertEquals("Rg validation failed. The rg pattern is invalid",
                    exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should validate Cpf validation with success")
    void shouldValidateCpfValidationWithSuccess() throws ParseException {
        Assertions.assertTrue(validation.verifyCpf(CustomerDTODataBuilder.builder().build().getCpf()));
    }

    @Test
    @DisplayName("Should validate Cpf validation with exception")
    void shouldValidateCpfValidationWithException(){

        try{
            validation.verifyCpf(CustomerDTODataBuilder.builder().withInvalidCpf().build().getCpf());
            Assertions.fail();
        }
        catch(InvalidRequestException | ParseException exception){
            Assertions.assertEquals("Cpf validation failed. The cpf pattern is invalid",
                    exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should validate Email validation with success")
    void shouldValidateEmailValidationWithSuccess() throws ParseException {
        Assertions.assertTrue(validation.verifyEmail(CustomerDTODataBuilder.builder().build().getEmail()));
    }

    @Test
    @DisplayName("Should validate Email validation with exception")
    void shouldValidateEmailValidationWithException(){

        try{
            validation.verifyEmail(CustomerDTODataBuilder.builder().withInvalidEmail().build().getEmail());
            Assertions.fail();
        }
        catch(InvalidRequestException | ParseException exception){
            Assertions.assertEquals("Email validation failed. The email pattern is invalid",
                    exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should validate address validation with success")
    void shouldValidateAddressWithSuccess() throws ParseException {
        Assertions.assertTrue(validation.verifyAddress(CustomerDTODataBuilder.builder().build().getAddress()));
    }

    @Test
    @DisplayName("Should validate Address validation with exception")
    void shouldValidateAddressValidationWithException() throws ParseException {

        AddressDTO address = AddressDTODataBuilder.builder().withTooLongNumber().build();
        CustomerDTO customer = CustomerDTODataBuilder.builder().build();

        customer.setAddress(address);

        try{
            validation.verifyAddress(customer.getAddress());
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("Address validation failed. The number must have only numbers with the " +
                            "max size of 5 characters.", exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should validate not null validation with success")
    void shouldValidateNotNullValidationWithSuccess() throws ParseException {
        Assertions.assertTrue(validation.notNull(CustomerDTODataBuilder.builder().build()));
    }

    @Test
    @DisplayName("Should validate not null validation with exception")
    void shouldValidateNotNullValidationWithException() throws ParseException {
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
    void shouldTestExistsWithSuccess() throws ParseException {

        Mockito.when(repository.findByRg(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(repository.findByCpf(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(repository.findByEmail(Mockito.any())).thenReturn(Optional.empty());

        Assertions.assertTrue(validation.exists(CustomerDTODataBuilder.builder().build(), repository));

    }

    @Test
    @DisplayName("Should test exists with all failures")
    void shouldTestExistsWithAllFailures() throws ParseException {

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
    void shouldTestExistsWithJustOneFailure() throws ParseException {

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
    void shouldValidateValidateRequestWithSuccess() throws ParseException {
        Assertions.assertTrue(validation.validateRequest(CustomerDTODataBuilder.builder().withRealisticBirthDate().build(), repository));
    }

}
