package br.com.loanapi.validations;

import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.mocks.dto.LoanDTODataBuilder;
import br.com.loanapi.models.dto.LoanDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

@SpringBootTest(classes = LoanValidation.class)
@DisplayName("Validation: Loan")
class LoanValidationTest {

    @InjectMocks
    LoanValidation validation;

    @Test
    @DisplayName("Should validate original value validation with success")
    void shouldValidateOriginalValueValidationWithSuccess() {
        Assertions.assertTrue(validation.verifyOriginalValue(LoanDTODataBuilder.builder().build().getOriginalValue()));
    }

    @Test
    @DisplayName("Should validate original value validation with exception")
    void shouldValidateOriginalValueValidationWithException(){

        try{
            validation.verifyOriginalValue(LoanDTODataBuilder.builder().withTooLongOriginalValue().build().getOriginalValue());
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("Original value validation failed",
                    exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should validate debit balance validation with success")
    void shouldValidateDebitBalanceValidationWithSuccess() {
        Assertions.assertTrue(validation.verifyDebitBalance(LoanDTODataBuilder.builder().build().getDebitBalance()));
    }

    @Test
    @DisplayName("Should validate debit balance validation with exception")
    void shouldValidateDebitBalanceValidationWithException(){

        try{
            validation.verifyDebitBalance(LoanDTODataBuilder.builder().withTooLongDebitBalance().build().getDebitBalance());
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("Debit balance validation failed",
                    exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should validate interest rate validation with success")
    void shouldValidateInterestRateValidationWithSuccess() {
        Assertions.assertTrue(validation.verifyInterestRate(LoanDTODataBuilder.builder().build().getInterestRate()));
    }

    @Test
    @DisplayName("Should validate interest rate validation with exception")
    void shouldValidateInterestRateValidationWithException(){

        try{
            validation.verifyInterestRate(LoanDTODataBuilder.builder().withTooLongInterestRate().build().getInterestRate());
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("Interest rate validation failed",
                    exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should validate number of installments validation with success")
    void shouldValidateNumberOfInstallmentsValidationWithSuccess() {
        Assertions.assertTrue(validation.verifyNumberOfInstallments(
                LoanDTODataBuilder.builder().build().getNumberOfInstallments()));
    }

    @Test
    @DisplayName("Should validate number of installments validation with exception")
    void shouldValidateNumberOfInstallmentsValidationWithException(){

        try{
            validation.verifyNumberOfInstallments(
                    LoanDTODataBuilder.builder().withTooLongNumberOfInstallments().build().getNumberOfInstallments());
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("Number of installments validation failed",
                    exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should validate not null validation with success")
    void shouldValidateNotNullValidationWithSuccess() {
        Assertions.assertTrue(validation.notNull(LoanDTODataBuilder.builder().build()));
    }

    @Test
    @DisplayName("Should validate not null validation with exception")
    void shouldValidateNotNullValidationWithException() {
        LoanDTO loan = LoanDTODataBuilder.builder().build();
        loan.setNumberOfInstallments(null);

        try{
            validation.notNull(loan);
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("Loan validation failed. Some of the attributes is null or empty",
                    exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should validate validate request with success")
    void shouldValidateValidateRequestWithSuccess() {
        Assertions.assertTrue(validation.validateRequest(LoanDTODataBuilder.builder().build()));
    }

}
