package br.com.loanapi.models.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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
    @JoinColumn(name = "id_city")
    private CityEntity city;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CustomerEntity> customers;

}
