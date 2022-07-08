package br.com.loanapi.models.dto;

import br.com.loanapi.models.enums.AmortizationEnum;
import br.com.loanapi.models.enums.PaymentDateEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

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

    @Transient
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date startDate;

    private Double originalValue;
    private Double debitBalance;
    private Double interestRate;
    private Integer numberOfInstallments;
    private PaymentDateEnum paymentDate;
    private AmortizationEnum amortization;
    private CustomerDTO customer;

    @Transient
    private List<InstallmentDTO> installments;
}
