package br.com.loanapi.models.dto;

import br.com.loanapi.models.enums.StateEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
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

    @JsonIgnore
    private Long id;

    @NotNull(message = "The street can't be null")
    private String street;

    @NotNull(message = "The neighborhood can't be null")
    private String neighborhood;

    @NotNull(message = "The street number can't be null")
    private Integer number;

    @NotNull(message = "The postal code can't be null")
    private String postalCode;

    @NotNull(message = "The city can't be null")
    private String city;

    @NotNull(message = "The state can't be null")
    private StateEnum state;

    private String complement;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<CustomerDTO> customers = new ArrayList<>();

    public void addCustomer(CustomerDTO customer) {
        customer.setAddress(this);
        this.customers.add(customer);
    }
}
