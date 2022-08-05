package br.com.installmentmicroservice.services;

import br.com.installmentmicroservice.mocks.LoanDTODataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@DisplayName("Service: InstallmentCalculationService")
class InstallmentCalculationServiceTest {

    @InjectMocks
    InstallmentCalculationService service;

    @Test
    @DisplayName("Should test installmentDistributorByAmortizationType method with SAC amortization")
    void shouldTestinstallmentDistributorByAmortizationTypeMethodWithSacAmortization() throws ParseException {
        Assertions.assertEquals("[InstallmentDTO(id=null, maturityDate=17-6-7, paymentDate=null, month=1, " +
                "amortization=500.0, interest=500.0, value=1000.0, notes=null, loan=null), InstallmentDTO(id=null, " +
                "maturityDate=17-7-5, paymentDate=null, month=2, amortization=500.0, interest=450.0, value=950.0, " +
                "notes=null, loan=null), InstallmentDTO(id=null, maturityDate=17-8-5, paymentDate=null, month=3, " +
                "amortization=500.0, interest=400.0, value=900.0, notes=null, loan=null), InstallmentDTO(id=null, " +
                "maturityDate=17-9-6, paymentDate=null, month=4, amortization=500.0, interest=350.0, value=850.0, " +
                "notes=null, loan=null), InstallmentDTO(id=null, maturityDate=17-10-5, paymentDate=null, month=5, " +
                "amortization=500.0, interest=300.0, value=800.0, notes=null, loan=null), InstallmentDTO(id=null, " +
                "maturityDate=17-11-5, paymentDate=null, month=6, amortization=500.0, interest=250.0, value=750.0, " +
                "notes=null, loan=null), InstallmentDTO(id=null, maturityDate=17-12-6, paymentDate=null, month=7, " +
                "amortization=500.0, interest=200.0, value=700.0, notes=null, loan=null), InstallmentDTO(id=null, " +
                "maturityDate=18-1-5, paymentDate=null, month=8, amortization=500.0, interest=150.0, value=650.0, " +
                "notes=null, loan=null), InstallmentDTO(id=null, maturityDate=18-2-7, paymentDate=null, month=9, " +
                "amortization=500.0, interest=100.0, value=600.0, notes=null, loan=null), InstallmentDTO(id=null, " +
                "maturityDate=18-3-7, paymentDate=null, month=10, amortization=500.0, interest=50.0, value=550.0, " +
                "notes=null, loan=null)]",
                service.installmentDistributorByAmortizationType(LoanDTODataBuilder.builder().build()).toString());
    }

    @Test
    @DisplayName("Should test installmentDistributorByAmortizationType method with PRICE amortization")
    void shouldTestinstallmentDistributorByAmortizationTypeMethodWithPriceAmortization() throws ParseException {
        Assertions.assertEquals("[InstallmentDTO(id=null, maturityDate=17-6-7, paymentDate=null, month=1, " +
                "amortization=313.73, interest=500.0, value=813.73, notes=null, loan=null), InstallmentDTO(id=null, " +
                "maturityDate=17-7-5, paymentDate=null, month=2, amortization=345.1, interest=468.63, value=813.73, " +
                "notes=null, loan=null), InstallmentDTO(id=null, maturityDate=17-8-5, paymentDate=null, month=3, " +
                "amortization=379.61, interest=434.12, value=813.73, notes=null, loan=null), InstallmentDTO(id=null, " +
                "maturityDate=17-9-6, paymentDate=null, month=4, amortization=417.57, interest=396.16, value=813.73," +
                " notes=null, loan=null), InstallmentDTO(id=null, maturityDate=17-10-5, paymentDate=null, month=5, " +
                "amortization=459.33, interest=354.4, value=813.73, notes=null, loan=null), InstallmentDTO(id=null, " +
                "maturityDate=17-11-5, paymentDate=null, month=6, amortization=505.26, interest=308.47, value=813.73, " +
                "notes=null, loan=null), InstallmentDTO(id=null, maturityDate=17-12-6, paymentDate=null, month=7, " +
                "amortization=555.79, interest=257.94, value=813.73, notes=null, loan=null), InstallmentDTO(id=null, " +
                "maturityDate=18-1-5, paymentDate=null, month=8, amortization=611.37, interest=202.36, value=813.73, " +
                "notes=null, loan=null), InstallmentDTO(id=null, maturityDate=18-2-7, paymentDate=null, month=9, " +
                "amortization=672.5, interest=141.23, value=813.73, notes=null, loan=null), InstallmentDTO(id=null, " +
                "maturityDate=18-3-7, paymentDate=null, month=10, amortization=739.75, interest=73.98, value=813.73, " +
                "notes=null, loan=null)]", service.installmentDistributorByAmortizationType(LoanDTODataBuilder.builder()
                .withPriceAmortization().build()).toString());
    }

}
