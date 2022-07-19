package br.com.loanapi.models.entities;

import br.com.loanapi.models.enums.PhoneTypeEnum;
import lombok.*;

import javax.persistence.*;

/** Class that contains all attributes of the object of type PhoneEntity
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @since 30/06/2022
 ** @email gabriellagrota23@gmail.com
 ** @github https://github.com/LagrotaGabriel/Loan-Project/blob/master/backend/loan-api/src/main/java/br/com/loanapi/models/entities/PhoneEntity.java */
@Entity
@Table(name = "TB_PHONE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class PhoneEntity {

    @Id
    @Column(name = "phone_id", nullable = false, updatable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
