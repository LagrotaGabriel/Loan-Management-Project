package br.com.loanapi.models.dto;

import lombok.*;

/** Class that contains all attributes of the object of type ScoreDTO
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @since 30/06/2022
 ** @email gabriellagrota23@gmail.com
 ** @github https://github.com/LagrotaGabriel/Loan-Project/blob/master/backend/loan-api/src/main/java/br/com/loanapi/models/dto/ScoreDTO.java */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ScoreDTO {
    private Long id;
    private Double pontuation;
    private CustomerDTO customer;
}
