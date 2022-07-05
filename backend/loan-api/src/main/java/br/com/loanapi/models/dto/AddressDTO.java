package br.com.loanapi.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Transient;
import java.util.List;

/** Class that contains all attributes of the object of type AddressDTO
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @since 30/06/2022
 ** @email gabriellagrota23@gmail.com
 ** @github https://github.com/LagrotaGabriel/Loan-Project/blob/master/backend/loan-api/src/main/java/br/com/loanapi/models/dto/AddressDTO.java */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class AddressDTO {

    @Transient
    private Long id;

    private String street;
    private String neighborhood;
    private Integer number;
    private String postalCode;
    private CityDTO city;

    @JsonProperty(value = "customers", access = JsonProperty.Access.READ_ONLY)
    private List<CustomerDTO> customers;
}
