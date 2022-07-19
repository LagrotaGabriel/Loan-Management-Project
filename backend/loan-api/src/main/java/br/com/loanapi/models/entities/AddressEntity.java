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
//@SequenceGenerator(allocationSize = 1, sequenceName = "sq_address", name = "address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String neighborhood;
    private Integer number;
    private String postalCode;
    private String city;

    @Enumerated(EnumType.STRING)
    private StateEnum state;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "address")
    private List<CustomerEntity> customers = new ArrayList<>();

}
