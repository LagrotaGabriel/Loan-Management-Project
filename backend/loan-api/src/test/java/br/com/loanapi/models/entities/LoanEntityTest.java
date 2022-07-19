package br.com.loanapi.models.entities;

import br.com.loanapi.mocks.entity.CustomerEntityDataBuilder;
import br.com.loanapi.mocks.entity.LoanEntityDataBuilder;
import br.com.loanapi.models.enums.AmortizationEnum;
import br.com.loanapi.models.enums.PaymentDateEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Entity: Loan")
class LoanEntityTest {

    @Test
    @DisplayName("Should test getters and setters")
    void shouldTestGettersAndSetters() {

        Assertions.assertEquals("LoanEntity(id=1, startDate=11-11-2011, originalValue=5000.0, " +
                        "debitBalance=2800.0, interestRate=10.0, numberOfInstallments=10, " +
                        "paymentDate=FIFTH_BUSINESS_DAY, amortization=SAC, customer=CustomerEntity(id=1, name=João, " +
                        "lastName=da Silva, birthDate=11-11-2011, signUpDate=11-11-2021, rg=55.626.926-4, " +
                        "cpf=391.534.277-44, email=joao@email.com, address=AddressEntity(id=1, street=Rua 9, " +
                        "neighborhood=Lauzane Paulista, number=583, postalCode=02442-090, city=São Paulo, " +
                        "state=SAO_PAULO, customers=null), score=ScoreEntity(id=1, pontuation=50.0, customer=null), " +
                        "phones=null, loans=null), installments=null)",
                LoanEntityDataBuilder.builder().build().toString());

    }

    @Test
    @DisplayName("Should test all args constructor")
    void shouldTestAllArgsConstructor() {

        LoanEntity loan = new LoanEntity(
                1L,
                "11-11-2011",
                5000.0,
                2800.0,
                10.0,
                10,
                PaymentDateEnum.FIFTH_BUSINESS_DAY,
                AmortizationEnum.SAC,
                CustomerEntityDataBuilder.builder().build(),
                null);

        Assertions.assertEquals("LoanEntity(id=1, startDate=11-11-2011, originalValue=5000.0, " +
                "debitBalance=2800.0, interestRate=10.0, numberOfInstallments=10, paymentDate=FIFTH_BUSINESS_DAY, " +
                "amortization=SAC, customer=CustomerEntity(id=1, name=João, lastName=da Silva, birthDate=11-11-2011, " +
                "signUpDate=11-11-2021, rg=55.626.926-4, cpf=391.534.277-44, email=joao@email.com, " +
                "address=AddressEntity(id=1, street=Rua 9, neighborhood=Lauzane Paulista, number=583, " +
                "postalCode=02442-090, city=São Paulo, state=SAO_PAULO, customers=null), score=ScoreEntity(id=1, " +
                "pontuation=50.0, customer=null), phones=null, loans=null), installments=null)", loan.toString());

    }

    @Test
    @DisplayName("Should test hashcode")
    void shouldTestHashcode(){
        LoanEntity loan = new LoanEntity();
        Assertions.assertEquals(739008883, loan.hashCode());
    }


}
