package br.com.loanapi.models.entities;

import br.com.loanapi.mocks.entity.AddressEntityDataBuilder;
import br.com.loanapi.mocks.entity.CustomerEntityDataBuilder;
import br.com.loanapi.utils.DateFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

@SpringBootTest
@DisplayName("Entity: Customer")
class CustomerEntityTest {

    @Test
    @DisplayName("Should test getters and setters")
    void shouldTestGettersAndSetters() throws ParseException {

        Assertions.assertEquals("CustomerEntity(id=1, name=João, lastName=da Silva, " +
                "birthDate=Fri Nov 11 00:00:00 BRST 2011, signUpDate=Thu Nov 11 00:00:00 BRT 2021, rg=55.626.926-4, " +
                "cpf=391.534.277-44, email=joao@email.com, address=AddressEntity(id=1, street=Rua 9, " +
                "neighborhood=Lauzane Paulista, number=583, postalCode=02442-090, city=CityEntity(id=1, city=São Paulo, " +
                "state=SAO_PAULO, addresses=null), customers=null), score=ScoreEntity(id=1, pontuation=50.0, " +
                "customer=null), phones=null, loans=null)", CustomerEntityDataBuilder.builder().build().toString());

    }

    @Test
    @DisplayName("Should test all args constructor")
    void shouldTestAllArgsConstructor() throws ParseException {

        ScoreEntity score = new ScoreEntity(1L, 50.0, null);
        CustomerEntity customer = new CustomerEntity(
                1L,
                "João",
                "da Silva",
                DateFormatter.convertStringToDateWithBrPattern("11-11-2011"),
                DateFormatter.convertStringToDateWithBrPattern("11-11-2021"),
                "55.626.926-4",
                "391.534.277-44",
                "joao@email.com",
                AddressEntityDataBuilder.builder().build(),
                score,
                null,
                null);

        Assertions.assertEquals("CustomerEntity(id=1, name=João, lastName=da Silva, " +
                "birthDate=Fri Nov 11 00:00:00 BRST 2011, signUpDate=Thu Nov 11 00:00:00 BRT 2021, rg=55.626.926-4, " +
                "cpf=391.534.277-44, email=joao@email.com, address=AddressEntity(id=1, street=Rua 9, " +
                "neighborhood=Lauzane Paulista, number=583, postalCode=02442-090, city=CityEntity(id=1, city=São Paulo, " +
                "state=SAO_PAULO, addresses=null), customers=null), score=ScoreEntity(id=1, pontuation=50.0, " +
                "customer=null), phones=null, loans=null)", customer.toString());

    }

    @Test
    @DisplayName("Should test hashcode")
    void shouldTestHashcodeMethod(){
        CustomerEntity customer = new CustomerEntity();
        Assertions.assertEquals(-195339799, customer.hashCode());
    }

}
