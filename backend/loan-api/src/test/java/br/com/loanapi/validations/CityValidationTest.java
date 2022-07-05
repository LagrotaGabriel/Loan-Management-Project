package br.com.loanapi.validations;

import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.mocks.dto.CityDTODataBuilder;
import br.com.loanapi.models.dto.CityDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = CityValidation.class)
@DisplayName("Validation: City")
class CityValidationTest {

    @InjectMocks
    CityValidation cityValidation;

    @Test
    @DisplayName("Should test city validation with success")
    void shouldTestCityValidationWithSuccess(){
        Assertions.assertTrue
                (cityValidation.verifyCity(CityDTODataBuilder.builder().build().getCity()));
    }

    @Test
    @DisplayName("Should test city validation with exception")
    void shouldTestCityValidationWithException(){
        CityDTO city = CityDTODataBuilder.builder().withTooLongCity().build();
        try{
            cityValidation.verifyCity(city.getCity());
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("City validation failed. The city name is too long (+65 characters).",
                    exception.getMessage());
        }
    }

    @Test
    @DisplayName("Should test not null validation with success")
    void shouldTestNotNullValidationWithSuccess(){
        Assertions.assertTrue(cityValidation.notNull(CityDTODataBuilder.builder().build()));
    }

    @Test
    @DisplayName("Should test not null validation with exception")
    void shouldTestNotNullValidationWithException(){
        CityDTO city = new CityDTO();
        try{
            cityValidation.notNull(city);
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("City validation failed. Some of the attributes is null " +
                    "or empty.", exception.getMessage());
        }
    }

    @Test
    @DisplayName("Should test validate request with success")
    void shouldTestValidateRequestWithSuccess(){
        Assertions.assertTrue(cityValidation.validateRequest(CityDTODataBuilder.builder().build()));
    }

    @Test
    @DisplayName("Should test validate request with exception")
    void shouldTestValidateRequestWithException(){
        CityDTO city = CityDTODataBuilder.builder().withTooLongCity().build();
        try{
            cityValidation.validateRequest(city);
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("City validation failed. The city name is too long (+65 characters).",
                    exception.getMessage());
        }

    }
}
