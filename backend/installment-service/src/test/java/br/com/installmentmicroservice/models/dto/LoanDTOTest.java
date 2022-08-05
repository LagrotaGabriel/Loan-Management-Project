package br.com.installmentmicroservice.models.dto;

import br.com.installmentmicroservice.mocks.LoanDTODataBuilder;
import br.com.installmentmicroservice.models.enums.AmortizationEnum;
import br.com.installmentmicroservice.models.enums.PaymentDateEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("DTO: Loan")
class LoanDTOTest {

    @Test
    @DisplayName("Should test getters and setters")
    void shouldTestGettersAndSetters()  {
        Assertions.assertEquals("LoanDTO(startDate=11-11-2011, originalValue=5000.0, interestRate=10.0, " +
                        "numberOfInstallments=1, paymentDate=LAST_BUSINESS_DAY, amortization=SAC)",
                LoanDTODataBuilder.builder().build().toString());
    }

    @Test
    @DisplayName("Should test all args constructor")
    void shouldTestAllArgsConstructor()  {

        LoanDTO loan = new LoanDTO(
                "11-11-2011",
                5000.0,
                10.0,
                10,
                PaymentDateEnum.LAST_BUSINESS_DAY,
                AmortizationEnum.SAC);

        Assertions.assertEquals("LoanDTO(startDate=11-11-2011, originalValue=5000.0, interestRate=10.0, " +
                "numberOfInstallments=10, paymentDate=LAST_BUSINESS_DAY, amortization=SAC)", loan.toString());

    }

    @Test
    @DisplayName("Should test hashcode")
    void shouldTestHashcode(){
        LoanDTO loan = new LoanDTO();
        Assertions.assertEquals(437864549, loan.hashCode());
    }

}
