package br.com.loanapi.models.entities;

import br.com.loanapi.models.enums.StateEnum;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TB_CITY")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@SequenceGenerator(allocationSize = 1, sequenceName = "sq_city", name = "city")
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "city")
    @Column(name = "city_id", unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = "city_desc", nullable = false, length = 100)
    private String city;

    @Enumerated(EnumType.STRING)
    @Column(name = "city_state", nullable = false)
    private StateEnum state;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AddressEntity> addresses;

}
