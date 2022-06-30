package br.com.loanapi.models.entities;

import br.com.loanapi.models.enums.PhoneTypeEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TB_PHONE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@SequenceGenerator(allocationSize = 1, sequenceName = "sq_phone", name = "phone")
public class PhoneEntity {

    @Id
    @Column(name = "phone_id", nullable = false, updatable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "phone")
    private Long id;

    @Column(name = "phone_prefix", nullable = false)
    private Integer prefix;

    @Column(name = "phone_number", nullable = false)
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(name = "phone_type", nullable = false)
    private PhoneTypeEnum phoneType;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

}
