package br.com.loanapi.models.entities;

import br.com.loanapi.models.enums.StateEnum;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/** Class that contains all attributes of the object of type AddressEntity
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @since 30/06/2022
 ** @email gabriellagrota23@gmail.com
 ** @github https://github.com/LagrotaGabriel/Loan-Project/blob/master/backend/loan-api/src/main/java/br/com/loanapi/models/entities/AddressEntity.java */
@Entity
@Table(name = "TB_ADDRESS")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street", length = 65)
    private String street;
    @Column(name = "neighborhood", length = 65)
    private String neighborhood;
    @Column(name = "number", length = 5)
    private Integer number;
    @Column(name = "postal_code", length = 9)
    private String postalCode;
    @Column(name = "city", length = 65)
    private String city;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private StateEnum state;

    @OneToMany(targetEntity = CustomerEntity.class, cascade={CascadeType.ALL}, mappedBy = "address", orphanRemoval = true)
    private List<CustomerEntity> customers = new ArrayList<>();

    public void addCustomer(CustomerEntity customer) {
        customer.setAddress(this);
        this.customers.add(customer);
    }

}
