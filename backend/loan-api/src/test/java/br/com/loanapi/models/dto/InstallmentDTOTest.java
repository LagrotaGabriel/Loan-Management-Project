package br.com.loanapi.models.dto;

import br.com.loanapi.mocks.dto.InstallmentDTODataBuilder;
import br.com.loanapi.mocks.dto.LoanDTODataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("DTO: Installment")
class InstallmentDTOTest {

    @Test
    @DisplayName("Should test getters and setters")
    void shouldTestGettersAndSetters() {

        Assertions.assertEquals("InstallmentDTO(id=1, maturityDate=11-11-2011, paymentDate=11-11-2021, " +
                        "expired=false, month=4, amortization=1000.0, interest=10.0, value=1100.0, loan=LoanDTO(id=1, " +
                        "startDate=11-11-2011, originalValue=5000.0, debitBalance=2800.0, interestRate=10.0, " +
                        "numberOfInstallments=10, paymentDate=FIFTH_BUSINESS_DAY, amortization=SAC, " +
                        "customer=CustomerDTO(id=1, name=João, lastName=da Silva, " +
                        "birthDate=11-11-2011, signUpDate=11-11-2021, rg=55.626.926-4, cpf=391.534.277-44, " +
                        "email=joao@email.com, pontuation=0.0, address=AddressDTO(id=1, street=Rua 9, " +
                        "neighborhood=Lauzane Paulista, number=583, postalCode=02442-090, city=São Paulo, " +
                        "state=SAO_PAULO, customers=[]), phones=[], loans=[]), installments=[]))",
                InstallmentDTODataBuilder.builder().build().toString());

    }

    @Test
    @DisplayName("Should test all args constructor")
    void shouldTestAllArgsConstructor() {

        InstallmentDTO installment = new InstallmentDTO(
                1L,
                "11-11-2011",
                "11-11-2021",
                false,
                4,
                1000.0,
                10.0,
                1100.0,
                LoanDTODataBuilder.builder().build());

        Assertions.assertEquals("InstallmentDTO(id=1, maturityDate=11-11-2011, paymentDate=11-11-2021, " +
                        "expired=false, month=4, amortization=1000.0, interest=10.0, value=1100.0, loan=LoanDTO(id=1, " +
                        "startDate=11-11-2011, originalValue=5000.0, debitBalance=2800.0, interestRate=10.0, " +
                        "numberOfInstallments=10, paymentDate=FIFTH_BUSINESS_DAY, amortization=SAC, " +
                        "customer=CustomerDTO(id=1, name=João, lastName=da Silva, birthDate=11-11-2011, " +
                        "signUpDate=11-11-2021, rg=55.626.926-4, cpf=391.534.277-44, email=joao@email.com, " +
                        "pontuation=0.0, address=AddressDTO(id=1, street=Rua 9, neighborhood=Lauzane Paulista, " +
                        "number=583, postalCode=02442-090, city=São Paulo, state=SAO_PAULO, customers=[]), " +
                        "phones=[], loans=[]), installments=[]))",
                installment.toString());

    }

    @Test
    @DisplayName("Should test hashcode")
    void shouldTestHashcode(){
        InstallmentDTO installment = new InstallmentDTO();
        Assertions.assertEquals(158117686, installment.hashCode());
    }

}
