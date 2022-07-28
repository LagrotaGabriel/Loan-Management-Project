package br.com.loanapi.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


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

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String maturityDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String paymentDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean expired;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer month;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double amortization;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double interest;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double value;

    @JsonIgnore
    private LoanDTO loan;
}
