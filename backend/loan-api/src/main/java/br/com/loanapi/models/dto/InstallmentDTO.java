package br.com.loanapi.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

/** Class that contains all attributes of the object of type InstallmentDTO
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @since 30/06/2022
 ** @email gabriellagrota23@gmail.com
 ** @github hhttps://github.com/LagrotaGabriel/Loan-Project/blob/master/backend/loan-api/src/main/java/br/com/loanapi/models/dto/InstallmentDTO.java */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class InstallmentDTO {

    @JsonIgnore
    private Long id;

    @NotNull(message = "Maturity date can't be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String maturityDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String paymentDate;

    @NotNull(message = "Installment month can't be null")
    private Integer month;

    @NotNull(message = "Amortization can't be null")
    private Double amortization;

    @NotNull(message = "Interest can't be null")
    private Double interest;

    @NotNull(message = "Value can't be null")
    private Double value;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LoanDTO loan;

}
