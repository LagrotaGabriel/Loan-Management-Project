package br.com.loanapi.models.dto;

import br.com.loanapi.models.enums.PhoneTypeEnum;
import lombok.*;

/** Class that contains all attributes of the object of type PhoneDTO
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @since 30/06/2022
 ** @email gabriellagrota23@gmail.com
 ** @github https://github.com/LagrotaGabriel/Loan-Project/blob/master/backend/loan-api/src/main/java/br/com/loanapi/models/dto/PhoneDTO.java */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class PhoneDTO {
    private Long id;
    private Integer prefix;
    private String number;
    private PhoneTypeEnum phoneType;
    private CustomerDTO customer;
}
