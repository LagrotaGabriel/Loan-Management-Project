package br.com.loanapi.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
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

    @JsonIgnore
    private Long id;

    private String name;
    private String lastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String birthDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String signUpDate;

    private String rg;
    private String cpf;
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private AddressDTO address;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ScoreDTO score;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private List<PhoneDTO> phones = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<LoanDTO> loans = new ArrayList<>();

    public void addPhone(PhoneDTO phoneDTO) {
        phoneDTO.setCustomer(this);
        this.phones.add(phoneDTO);
    }

    public void addLoan(LoanDTO loanDTO) {
        loanDTO.setCustomer(this);
        this.loans.add(loanDTO);
    }

}
