package br.com.loanapi.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/** Class that contains all attributes of the object of type CustomerDTO
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @since 30/06/2022
 ** @email gabriellagrota23@gmail.com
 ** @github https://github.com/LagrotaGabriel/Loan-Project/blob/master/backend/loan-api/src/main/java/br/com/loanapi/models/dto/CustomerDTO.java */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CustomerDTO {

    @Transient
    private Long id;
    private String name;
    private String lastName;
    private Date birthDate;
    private Date signUpDate;
    private String rg;
    private String cpf;
    private String email;
    private AddressDTO address;

    @Transient
    private ScoreDTO score;

    @JsonProperty(value = "customers", access = JsonProperty.Access.READ_ONLY)
    private List<PhoneDTO> phones;

    @JsonProperty(value = "customers", access = JsonProperty.Access.READ_ONLY)
    private List<LoanDTO> loans;

}
