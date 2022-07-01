package br.com.loanapi.models.entities;

import br.com.loanapi.mocks.InstallmentEntityDataBuilder;
import br.com.loanapi.mocks.LoanEntityDataBuilder;
import br.com.loanapi.utils.DateFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

@SpringBootTest
@DisplayName("Entity: Installment")
class InstallmentEntityTest {

    @Test
    @DisplayName("Should test getters and setters")
    void shouldTestGettersAndSetters() throws ParseException {

        Assertions.assertEquals("InstallmentEntity(id=1, maturityDate=Fri Nov 11 00:00:00 BRST 2011, " +
                "paymentDate=Thu Nov 11 00:00:00 BRT 2021, expired=false, month=4, amortization=1000.0, interest=10.0, " +
                "value=1100.0, loan=LoanEntity(id=1, startDate=Fri Nov 11 00:00:00 BRST 2011, originalValue=5000.0, " +
                "debitBalance=2800.0, interestRate=10.0, numberOfInstallments=10, paymentDate=FIFTH_BUSINESS_DAY, " +
                "amortization=SAC, customer=CustomerEntity(id=1, name=João, lastName=da Silva, " +
                "birthDate=Fri Nov 11 00:00:00 BRST 2011, signUpDate=Thu Nov 11 00:00:00 BRT 2021, " +
                "rg=55.626.926-4, cpf=391.534.277-44, email=joao@email.com, address=AddressEntity(id=1, street=Rua 9, " +
                "neighborhood=Lauzane Paulista, number=583, postalCode=02442-090, city=CityEntity(id=1, city=São Paulo, " +
                "state=SAO_PAULO, addresses=null), customers=null), score=ScoreEntity(id=1, pontuation=50.0, " +
                "customer=null), phones=null, loans=null), installments=null))",
                InstallmentEntityDataBuilder.builder().build().toString());

    }

    @Test
    @DisplayName("Should test all args constructor")
    void shouldTestAllArgsConstructor() throws ParseException {

        InstallmentEntity installment = new InstallmentEntity(
                1L,
                DateFormatter.convertStringToDateWithBrPattern("11-11-2011"),
                DateFormatter.convertStringToDateWithBrPattern("11-11-2021"),
                false,
                4,
                1000.0,
                10.0,
                1100.0,
                LoanEntityDataBuilder.builder().build());

        Assertions.assertEquals("InstallmentEntity(id=1, maturityDate=Fri Nov 11 00:00:00 BRST 2011, " +
                        "paymentDate=Thu Nov 11 00:00:00 BRT 2021, expired=false, month=4, amortization=1000.0, interest=10.0, " +
                        "value=1100.0, loan=LoanEntity(id=1, startDate=Fri Nov 11 00:00:00 BRST 2011, originalValue=5000.0, " +
                        "debitBalance=2800.0, interestRate=10.0, numberOfInstallments=10, paymentDate=FIFTH_BUSINESS_DAY, " +
                        "amortization=SAC, customer=CustomerEntity(id=1, name=João, lastName=da Silva, " +
                        "birthDate=Fri Nov 11 00:00:00 BRST 2011, signUpDate=Thu Nov 11 00:00:00 BRT 2021, " +
                        "rg=55.626.926-4, cpf=391.534.277-44, email=joao@email.com, address=AddressEntity(id=1, street=Rua 9, " +
                        "neighborhood=Lauzane Paulista, number=583, postalCode=02442-090, city=CityEntity(id=1, city=São Paulo, " +
                        "state=SAO_PAULO, addresses=null), customers=null), score=ScoreEntity(id=1, pontuation=50.0, " +
                        "customer=null), phones=null, loans=null), installments=null))", installment.toString());

    }

    @Test
    @DisplayName("Should test hashcode")
    void shouldTestHashcode(){
        InstallmentEntity installment = new InstallmentEntity();
        Assertions.assertEquals(158117686, installment.hashCode());
    }

}
