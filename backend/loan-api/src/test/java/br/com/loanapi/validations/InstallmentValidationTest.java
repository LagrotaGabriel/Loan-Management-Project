package br.com.loanapi.validations;

import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.mocks.dto.InstallmentDTODataBuilder;
import br.com.loanapi.models.dto.InstallmentDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

@SpringBootTest(classes = InstallmentValidation.class)
@DisplayName("Validation: Installment")
class InstallmentValidationTest {

    @InjectMocks
    InstallmentValidation validation;

    @Test
    @DisplayName("Should validate month validation with success")
    void shouldValidateMonthValidationWithSuccess() {
        Assertions.assertTrue(validation.verifyMonth(InstallmentDTODataBuilder.builder().build().getMonth()));
    }

    @Test
    @DisplayName("Should validate month validation with exception")
    void shouldValidateMonthValidationWithException(){

        try{
            validation.verifyMonth(InstallmentDTODataBuilder.builder().withTooLongMonth().build().getMonth());
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("Installment validation failed. The month number may be between 1-999",
                    exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should validate amortization validation with success")
    void shouldValidateAmortizationValidationWithSuccess() {
        Assertions.assertTrue(validation.verifyAmortization(InstallmentDTODataBuilder.builder().build().getAmortization()));
    }

    @Test
    @DisplayName("Should validate amortization validation with exception")
    void shouldValidateAmortizationValidationWithException(){

        try{
            validation.verifyAmortization(InstallmentDTODataBuilder.builder().withTooLongAmortization().build().getAmortization());
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("Amortization validation failed", exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should validate interest validation with success")
    void shouldValidateInterestValidationWithSuccess() {
        Assertions.assertTrue(validation.verifyInterest(InstallmentDTODataBuilder.builder().build().getInterest()));
    }

    @Test
    @DisplayName("Should validate interest validation with exception")
    void shouldValidateInterestValidationWithException(){

        try{
            validation.verifyInterest(InstallmentDTODataBuilder.builder().withTooLongInterest().build().getInterest());
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("Interest validation failed", exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should validate value validation with success")
    void shouldValidateValueValidationWithSuccess() {
        Assertions.assertTrue(validation.verifyValue(InstallmentDTODataBuilder.builder().build().getValue()));
    }

    @Test
    @DisplayName("Should validate Value validation with exception")
    void shouldValidateValueValidationWithException(){

        try{
            validation.verifyValue(InstallmentDTODataBuilder.builder().withTooLongValue().build().getValue());
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("Value validation failed", exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should validate not null validation with success")
    void shouldValidateNotNullValidationWithSuccess() {
        Assertions.assertTrue(validation.notNull(InstallmentDTODataBuilder.builder().build()));
    }

    @Test
    @DisplayName("Should validate not null validation with exception")
    void shouldValidateNotNullValidationWithException() {
        InstallmentDTO installment = InstallmentDTODataBuilder.builder().build();
        installment.setLoan(null);

        try{
            validation.notNull(installment);
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("Installment validation failed. Some of the attributes is null or empty",
                    exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should validate validate request with success")
    void shouldValidateValidateRequestWithSuccess() {
        Assertions.assertTrue(validation.validateRequest(InstallmentDTODataBuilder.builder().build()));
    }

}
