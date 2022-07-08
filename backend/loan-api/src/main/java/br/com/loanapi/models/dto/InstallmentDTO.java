package br.com.loanapi.models.dto;

import lombok.*;

import javax.persistence.Transient;
import java.util.Date;

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

    @Transient
    private Long id;

    private Date maturityDate;
    private Date paymentDate;
    private Boolean expired;
    private Integer month;
    private Double amortization;
    private Double interest;
    private Double value;

    @Transient
    private LoanDTO loan;
}
