package br.com.loanapi.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
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

    @NotNull(message = "The customer name can't be null")
    private String name;

    @NotNull(message = "The customer last name can't be null")
    private String lastName;

    @NotNull(message = "The customer birth date can't be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String birthDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String signUpDate = LocalDateTime.now().toString();

    @NotNull(message = "The customer rg can't be null")
    private String rg;

    @NotNull(message = "The customer cpf can't be null")
    private String cpf;

    @NotNull(message = "The customer email can't be null")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private AddressDTO address;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ScoreDTO score;

    @NotNull(message = "The customer phone can't be null")
    private List<PhoneDTO> phones = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<LoanDTO> loans = new ArrayList<>();

    public void addPhone(PhoneDTO phone) {
        phone.setCustomer(this);
        this.getPhones().add(phone);
    }

    public void setPhoneList(List<PhoneDTO> phoneDTO) {
        for (PhoneDTO phone: phones) {
            phone.setCustomer(this);
        }
        this.setPhones(phoneDTO);
    }

    public void addLoan(LoanDTO loanDTO) {
        loanDTO.setCustomer(this);
        this.loans.add(loanDTO);
    }

}
