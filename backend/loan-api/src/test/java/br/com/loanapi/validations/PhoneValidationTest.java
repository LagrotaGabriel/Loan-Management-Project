package br.com.loanapi.validations;

import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.mocks.dto.LoanDTODataBuilder;
import br.com.loanapi.mocks.dto.PhoneDTODataBuilder;
import br.com.loanapi.models.dto.LoanDTO;
import br.com.loanapi.models.dto.PhoneDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

@SpringBootTest(classes = PhoneValidation.class)
@DisplayName("Validation: Phone")
class PhoneValidationTest {

    @InjectMocks
    PhoneValidation validation;

    @Test
    @DisplayName("Should validate prefix validation with success")
    void shouldValidatePrefixValidationWithSuccess() throws ParseException {
        Assertions.assertTrue(validation.verifyPrefix(PhoneDTODataBuilder.builder().build().getPrefix()));
    }

    @Test
    @DisplayName("Should validate prefix validation with exception")
    void shouldValidatePrefixValidationWithException(){

        try{
            validation.verifyPrefix(PhoneDTODataBuilder.builder().withWrongPrefix().build().getPrefix());
            Assertions.fail();
        }
        catch(InvalidRequestException | ParseException exception){
            Assertions.assertEquals("Prefix validation failed. The pattern must be two numbers. Example: 99",
                    exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should validate phone number validation with success")
    void shouldValidatePhoneNumberValidationWithSuccess() throws ParseException {
        Assertions.assertTrue(validation.verifyNumber(PhoneDTODataBuilder.builder().build().getNumber()));
    }

    @Test
    @DisplayName("Should validate phone number validation with exception")
    void shouldValidatePhoneNumberValidationWithException(){

        try{
            validation.verifyNumber(PhoneDTODataBuilder.builder().withWrongNumber().build().getNumber());
            Assertions.fail();
        }
        catch(InvalidRequestException | ParseException exception){
            Assertions.assertEquals("Phone number validation failed. Pattern example: xxxxx-xxxx (for mobile) or xxxx-xxxx (for landline)",
                    exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should validate not null validation with success")
    void shouldValidateNotNullValidationWithSuccess() throws ParseException {
        Assertions.assertTrue(validation.notNull(PhoneDTODataBuilder.builder().build()));
    }

    @Test
    @DisplayName("Should validate not null validation with exception")
    void shouldValidateNotNullValidationWithException() throws ParseException {
        PhoneDTO phone = PhoneDTODataBuilder.builder().build();
        phone.setNumber(null);

        try{
            validation.notNull(phone);
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("Phone validation failed. Some of the attributes is null or empty",
                    exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should validate validate request with success")
    void shouldValidateValidateRequestWithSuccess() throws ParseException {
        Assertions.assertTrue(validation.validateRequest(PhoneDTODataBuilder.builder().build()));
    }

}
