package br.com.installmentmicroservice.models.dto;

import br.com.installmentmicroservice.mocks.InstallmentDTODataBuilder;
import br.com.installmentmicroservice.mocks.LoanDTODataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
@DisplayName("DTO: Installment")
class InstallmentDTOTest {

    @Test
    @DisplayName("Should test getters and setters")
    void shouldTestGettersAndSetters() {
        Assertions.assertEquals("InstallmentDTO(id=1, maturityDate=11-11-2011, paymentDate=11-11-2011, month=4, " +
                "amortization=1000.0, interest=10.0, value=1100.0, notes=[], loan=LoanDTO(startDate=11-11-2011, " +
                "originalValue=5000.0, interestRate=10.0, numberOfInstallments=10, paymentDate=FIFTH_BUSINESS_DAY, " +
                "amortization=SAC))", InstallmentDTODataBuilder.builder().build().toString());
    }

    @Test
    @DisplayName("Should test all args constructor")
    void shouldTestAllArgsConstructor() {

        InstallmentDTO installment = new InstallmentDTO(
                1L,
                "11-11-2011",
                "11-11-2011",
                4,
                1000.0,
                10.0,
                1100.0,
                "[]",
                LoanDTODataBuilder.builder().build());

        Assertions.assertEquals("InstallmentDTO(id=1, maturityDate=11-11-2011, paymentDate=11-11-2011, month=4, " +
                "amortization=1000.0, interest=10.0, value=1100.0, notes=[], loan=LoanDTO(startDate=11-11-2011, " +
                "originalValue=5000.0, interestRate=10.0, numberOfInstallments=10, paymentDate=FIFTH_BUSINESS_DAY, " +
                "amortization=SAC))", installment.toString());

    }

    @Test
    @DisplayName("Should test hashcode")
    void shouldTestHashcode(){
        InstallmentDTO installment = new InstallmentDTO();
        Assertions.assertEquals(158117686, installment.hashCode());
    }
}
