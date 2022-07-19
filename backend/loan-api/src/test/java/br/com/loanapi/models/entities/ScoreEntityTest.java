package br.com.loanapi.models.entities;

import br.com.loanapi.mocks.entity.CustomerEntityDataBuilder;
import br.com.loanapi.mocks.entity.ScoreEntityDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

@SpringBootTest
@DisplayName("Entity: Score")
class ScoreEntityTest {

    @Test
    @DisplayName("Should test getters and setters")
    void shouldTestGettersAndSetters() {
        Assertions.assertEquals("ScoreEntity(id=1, pontuation=50.0, customer=CustomerEntity(id=1, " +
                "name=João, lastName=da Silva, birthDate=11-11-2011, signUpDate=11-11-2021, rg=55.626.926-4, " +
                "cpf=391.534.277-44, email=joao@email.com, address=AddressEntity(id=1, street=Rua 9, " +
                "neighborhood=Lauzane Paulista, number=583, postalCode=02442-090, city=São Paulo, state=SAO_PAULO, " +
                "customers=null), score=ScoreEntity(id=1, pontuation=50.0, customer=null), phones=null, loans=null))",
                ScoreEntityDataBuilder.builder().build().toString());
    }

    @Test
    @DisplayName("Should test all args constructor")
    void shouldTestAllArgsConstructor() {
        ScoreEntity scoreEntity = new ScoreEntity(1L, 50.0, CustomerEntityDataBuilder.builder().build());
        Assertions.assertEquals("ScoreEntity(id=1, pontuation=50.0, customer=CustomerEntity(id=1, name=João, " +
                "lastName=da Silva, birthDate=11-11-2011, signUpDate=11-11-2021, rg=55.626.926-4, cpf=391.534.277-44, " +
                "email=joao@email.com, address=AddressEntity(id=1, street=Rua 9, neighborhood=Lauzane Paulista, " +
                "number=583, postalCode=02442-090, city=São Paulo, state=SAO_PAULO, customers=null), " +
                "score=ScoreEntity(id=1, pontuation=50.0, customer=null), phones=null, loans=null))",
                scoreEntity.toString());
    }

    @Test
    @DisplayName("Should test hashcode")
    void shouldTestHashcode(){
        ScoreEntity score = new ScoreEntity();
        Assertions.assertEquals(357642, score.hashCode());
    }

}
