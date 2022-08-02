package br.com.loanapi.models.dto;

import br.com.loanapi.mocks.dto.CustomerDTODataBuilder;
import br.com.loanapi.mocks.dto.LoanDTODataBuilder;
import br.com.loanapi.models.enums.AmortizationEnum;
import br.com.loanapi.models.enums.PaymentDateEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
@DisplayName("DTO: Loan")
class LoanDTOTest {

    @Test
    @DisplayName("Should test getters and setters")
    void shouldTestGettersAndSetters()  {

        Assertions.assertEquals("LoanDTO(id=1, startDate=11-11-2011, originalValue=5000.0, " +
                        "debitBalance=2800.0, interestRate=10.0, numberOfInstallments=10, " +
                        "paymentDate=FIFTH_BUSINESS_DAY, amortization=SAC, " +
                        "customer=CustomerDTO(id=1, name=João, lastName=da Silva, birthDate=11-11-2011, " +
                        "signUpDate=11-11-2021, rg=55.626.926-4, cpf=391.534.277-44, email=joao@email.com, " +
                        "pontuation=0.0, address=AddressDTO(id=1, street=Rua 9, neighborhood=Lauzane Paulista, " +
                        "number=583, postalCode=02442-090, city=São Paulo, state=SAO_PAULO, customers=[]), phones=[], " +
                        "loans=[]), installments=[])",
                LoanDTODataBuilder.builder().build().toString());

    }

    @Test
    @DisplayName("Should test all args constructor")
    void shouldTestAllArgsConstructor()  {

        LoanDTO loan = new LoanDTO(
                1L,
                "11-11-2011",
                5000.0,
                2800.0,
                10.0,
                10,
                PaymentDateEnum.FIFTH_BUSINESS_DAY,
                AmortizationEnum.SAC,
                CustomerDTODataBuilder.builder().build(),
                new ArrayList<>());

        Assertions.assertEquals("LoanDTO(id=1, startDate=11-11-2011, originalValue=5000.0, debitBalance=2800.0," +
                " interestRate=10.0, numberOfInstallments=10, paymentDate=FIFTH_BUSINESS_DAY, amortization=SAC, " +
                "customer=CustomerDTO(id=1, name=João, lastName=da Silva, birthDate=11-11-2011, signUpDate=11-11-2021, " +
                "rg=55.626.926-4, cpf=391.534.277-44, email=joao@email.com, pontuation=0.0, address=AddressDTO(id=1, " +
                "street=Rua 9, neighborhood=Lauzane Paulista, number=583, postalCode=02442-090, city=São Paulo, " +
                "state=SAO_PAULO, customers=[]), phones=[], loans=[]), installments=[])", loan.toString());

    }

    @Test
    @DisplayName("Should test hashcode")
    void shouldTestHashcode(){
        LoanDTO loan = new LoanDTO();
        Assertions.assertEquals(739008883, loan.hashCode());
    }


}
