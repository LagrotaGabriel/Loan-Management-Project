package br.com.loanapi.models.entities;

import lombok.*;

import javax.persistence.*;
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
@SequenceGenerator(allocationSize = 1, sequenceName = "sq_address", name = "address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "address")
    @Column(name = "address_id", unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = "address_street", length = 100, nullable = false)
    private String street;

    @Column(name = "address_neighborhood", length = 100)
    private String neighborhood;

    @Column(name = "address_number")
    private Integer number;

    @Column(name = "address_postalCode", length = 9)
    private String postalCode;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityEntity city;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CustomerEntity> customers;

}
