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
        Assertions.assertNotNull(service.installmentDistributorByAmortizationType(LoanDTODataBuilder.builder().build()));
    }

    @Test
    @DisplayName("Should test installmentDistributorByAmortizationType method with PRICE amortization")
    void shouldTestinstallmentDistributorByAmortizationTypeMethodWithPriceAmortization() throws ParseException {

        Assertions.assertNotNull( service.installmentDistributorByAmortizationType(LoanDTODataBuilder.builder()
                .withPriceAmortization().build()));
    }

}
