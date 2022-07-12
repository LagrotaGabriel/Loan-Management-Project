package br.com.loanapi.validations;

import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.mocks.dto.PhoneDTODataBuilder;
import br.com.loanapi.mocks.dto.ScoreDTODataBuilder;
import br.com.loanapi.models.dto.PhoneDTO;
import br.com.loanapi.models.dto.ScoreDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

@SpringBootTest(classes = ScoreValidation.class)
@DisplayName("Validation: Score")
class ScoreValidationTest {

    @InjectMocks
    ScoreValidation validation = new ScoreValidation();

    @Test
    @DisplayName("Should validate score validation with success")
    void shouldValidateScoreValidationWithSuccess() throws ParseException {
        Assertions.assertTrue(validation.verifyPontuation(ScoreDTODataBuilder.builder().build().getPontuation()));
    }

    @Test
    @DisplayName("Should validate score validation with exception")
    void shouldValidateScoreValidationWithException(){

        try{
            validation.verifyPontuation(ScoreDTODataBuilder.builder().withWrongPontuation().build().getPontuation());
            Assertions.fail();
        }
        catch(InvalidRequestException | ParseException exception){
            Assertions.assertEquals("Pontuation validation failed. The pontuation must be between 0-100",
                    exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should validate not null validation with success")
    void shouldValidateNotNullValidationWithSuccess() throws ParseException {
        Assertions.assertTrue(validation.notNull(ScoreDTODataBuilder.builder().build()));
    }

    @Test
    @DisplayName("Should validate not null validation with exception")
    void shouldValidateNotNullValidationWithException() throws ParseException {
        ScoreDTO score = ScoreDTODataBuilder.builder().build();
        score.setPontuation(null);

        try{
            validation.notNull(score);
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("Score validation failed. Some of the attributes is null or empty",
                    exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should validate validate request with success")
    void shouldValidateValidateRequestWithSuccess() throws ParseException {
        Assertions.assertTrue(validation.validateRequest(ScoreDTODataBuilder.builder().build()));
    }

}
