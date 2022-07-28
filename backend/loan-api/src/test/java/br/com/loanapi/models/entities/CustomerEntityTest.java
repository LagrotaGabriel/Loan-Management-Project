package br.com.loanapi.models.entities;

import br.com.loanapi.mocks.entity.AddressEntityDataBuilder;
import br.com.loanapi.mocks.entity.CustomerEntityDataBuilder;
import br.com.loanapi.mocks.entity.LoanEntityDataBuilder;
import br.com.loanapi.mocks.entity.PhoneEntityDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@DisplayName("Entity: Customer")
class CustomerEntityTest {

    @Test
    @DisplayName("Should test getters and setters")
    void shouldTestGettersAndSetters() {

        Assertions.assertEquals("CustomerEntity(id=1, name=João, lastName=da Silva, birthDate=11-11-2011, " +
                        "signUpDate=11-11-2021, rg=55.626.926-4, cpf=391.534.277-44, email=joao@email.com, " +
                        "pontuation=null, address=AddressEntity(id=1, street=Rua 9, neighborhood=Lauzane Paulista, " +
                        "number=583, postalCode=02442-090, city=São Paulo, state=SAO_PAULO, customers=null), " +
                        "phones=null, loans=null)",
                CustomerEntityDataBuilder.builder().build().toString());

    }

    @Test
    @DisplayName("Should test all args constructor")
    void shouldTestAllArgsConstructor() {

        CustomerEntity customer = new CustomerEntity(
                1L,
                "João",
                "da Silva",
                "11-11-2011",
                "11-11-2021",
                "55.626.926-4",
                "391.534.277-44",
                "joao@email.com",
                100.0,
                AddressEntityDataBuilder.builder().build(),
                null,
                null);

        Assertions.assertEquals("CustomerEntity(id=1, name=João, lastName=da Silva, birthDate=11-11-2011, " +
                "signUpDate=11-11-2021, rg=55.626.926-4, cpf=391.534.277-44, email=joao@email.com, pontuation=100.0, " +
                "address=AddressEntity(id=1, street=Rua 9, neighborhood=Lauzane Paulista, number=583, " +
                "postalCode=02442-090, city=São Paulo, state=SAO_PAULO, customers=null), phones=null, loans=null)", customer.toString());

    }

    @Test
    @DisplayName("Should test addPhone method")
    void shouldTestAddPhoneMethod() {
        CustomerEntity customer = CustomerEntityDataBuilder.builder().build();
        customer.setPhones(new ArrayList<>());

        customer.addPhone(PhoneEntityDataBuilder.builder().build());
        Assertions.assertNotEquals(0, customer.getPhones().size());
    }

    @Test
    @DisplayName("Should test addLoan method")
    void shouldTestAddLoanMethod() {

        CustomerEntity customer = CustomerEntityDataBuilder.builder().build();
        customer.setLoans(new ArrayList<>());

        customer.addLoan(LoanEntityDataBuilder.builder().build());
        Assertions.assertNotEquals(0, customer.getLoans().size());
    }

    @Test
    @DisplayName("Should test hashcode")
    void shouldTestHashcodeMethod(){
        CustomerEntity customer = new CustomerEntity();
        Assertions.assertEquals(-195342319, customer.hashCode());
    }

}
