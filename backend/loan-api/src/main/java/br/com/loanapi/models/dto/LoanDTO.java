package br.com.loanapi.models.dto;

import br.com.loanapi.models.enums.AmortizationEnum;
import br.com.loanapi.models.enums.PaymentDateEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
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

    @JsonIgnore
    private Long id;

    @NotNull(message = "Start date can't be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String startDate;

    @NotNull(message = "Original value can't be null")
    private Double originalValue;

    @NotNull(message = "Debit balance can't be null")
    private Double debitBalance;

    @NotNull(message = "Interest rate can't be null")
    private Double interestRate;

    @NotNull(message = "Number of installments can't be null")
    private Integer numberOfInstallments;

    @NotNull(message = "Payment date can't be null")
    private PaymentDateEnum paymentDate;

    @NotNull(message = "Amortization can't be null")
    private AmortizationEnum amortization;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long customerJsonId;

    @JsonIgnore
    private CustomerDTO customer;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<InstallmentDTO> installments = new ArrayList<>();
}
