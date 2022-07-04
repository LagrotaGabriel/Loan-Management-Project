package br.com.loanapi.models.dto;

import br.com.loanapi.mocks.dto.CustomerDTODataBuilder;
import br.com.loanapi.mocks.dto.ScoreDTODataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

@SpringBootTest
@DisplayName("DTO: Score")
class ScoreDTOTest {

    @Test
    @DisplayName("Should test getters and setters")
    void shouldTestGettersAndSetters() throws ParseException {
        Assertions.assertEquals("ScoreDTO(id=1, pontuation=50.0, customer=CustomerDTO(id=1, name=João, " +
                "lastName=da Silva, birthDate=Fri Nov 11 00:00:00 BRST 2011, signUpDate=Thu Nov 11 00:00:00 BRT 2021, " +
                "rg=55.626.926-4, cpf=391.534.277-44, email=joao@email.com, address=AddressDTO(id=1, street=Rua 9, " +
                "neighborhood=Lauzane Paulista, number=583, postalCode=02442-090, city=CityDTO(id=1, city=São Paulo, " +
                "state=SAO_PAULO, addresses=null), customers=null), score=ScoreDTO(id=1, pontuation=50.0, " +
                "customer=null), phones=null, loans=null))", ScoreDTODataBuilder.builder().build().toString());
    }

    @Test
    @DisplayName("Should test all args constructor")
    void shouldTestAllArgsConstructor() throws ParseException {
        ScoreDTO scoreDTO = new ScoreDTO(1L, 50.0, CustomerDTODataBuilder.builder().build());
        Assertions.assertEquals("ScoreDTO(id=1, pontuation=50.0, customer=CustomerDTO(id=1, name=João, " +
                "lastName=da Silva, birthDate=Fri Nov 11 00:00:00 BRST 2011, signUpDate=Thu Nov 11 00:00:00 BRT 2021, " +
                "rg=55.626.926-4, cpf=391.534.277-44, email=joao@email.com, address=AddressDTO(id=1, street=Rua 9, " +
                "neighborhood=Lauzane Paulista, number=583, postalCode=02442-090, city=CityDTO(id=1, city=São Paulo, " +
                "state=SAO_PAULO, addresses=null), customers=null), score=ScoreDTO(id=1, pontuation=50.0, " +
                "customer=null), phones=null, loans=null))", scoreDTO.toString());
    }

    @Test
    @DisplayName("Should test hashcode")
    void shouldTestHashcode(){
        ScoreDTO score = new ScoreDTO();
        Assertions.assertEquals(357642, score.hashCode());
    }

}
