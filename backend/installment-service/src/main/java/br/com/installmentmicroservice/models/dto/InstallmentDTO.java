package br.com.installmentmicroservice.models.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


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
    private Long id;
    private String maturityDate;
    private String paymentDate;
    private Integer month;
    private Double amortization;
    private Double interest;
    private Double value;
    private String notes;
    private LoanDTO loan;
}
