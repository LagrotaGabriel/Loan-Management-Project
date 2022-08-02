package br.com.installmentmicroservice.models.dto;

import br.com.installmentmicroservice.models.enums.AmortizationEnum;
import br.com.installmentmicroservice.models.enums.PaymentDateEnum;
import lombok.*;

/** Class that contains all attributes of the object of type LoanDTO
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @since 30/06/2022
 ** @email gabriellagrota23@gmail.com
 ** @github https://github.com/LagrotaGabriel/Loan-Project/blob/master/backend/loan-api/src/main/java/br/com/loanapi/models/dto/LoanDTO.java */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class LoanDTO {

    private String startDate;
    private Double originalValue;
    private Double interestRate;
    private Integer numberOfInstallments;
    private PaymentDateEnum paymentDate;
    private AmortizationEnum amortization;

}
