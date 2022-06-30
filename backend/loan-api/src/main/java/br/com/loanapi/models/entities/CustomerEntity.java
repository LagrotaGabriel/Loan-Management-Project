package br.com.loanapi.models.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TB_CUSTOMER")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@SequenceGenerator(allocationSize = 1, sequenceName = "sq_customer", name = "customer")
public class CustomerEntity {

    @Id
    @Column(name = "customer_id", nullable = false, updatable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "customer")
    private Long id;

    @Column(name = "customer_name", nullable = false, length = 50)
    private String name;

    @Column(name = "customer_lastNname", nullable = false, length = 100)
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(name = "customer_birthDate")
    private Date birthDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "customer_signUpDate")
    private Date signUpDate;

    @Column(name = "customer_rg", length = 12)
    private String rg;

    @Column(name = "customer_cpf", length = 14)
    private String cpf;

    @Column(name = "customer_email", length = 100)
    private String email;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "score_id", referencedColumnName = "score_id")
    private ScoreEntity score;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PhoneEntity> phones;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LoanEntity> loans;

}
