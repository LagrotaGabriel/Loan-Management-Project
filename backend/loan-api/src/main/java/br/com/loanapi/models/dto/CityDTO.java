package br.com.loanapi.models.dto;

import br.com.loanapi.models.enums.StateEnum;
import lombok.*;

import java.util.List;

/** Class that contains all attributes of the object of type CityDTO
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @since 30/06/2022
 ** @email gabriellagrota23@gmail.com
 ** @github https://github.com/LagrotaGabriel/Loan-Project/blob/master/backend/loan-api/src/main/java/br/com/loanapi/models/dto/CityDTO.java */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CityDTO {
    private Long id;
    private String city;
    private StateEnum state;
    private List<AddressDTO> addresses;
}
