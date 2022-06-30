package br.com.loanapi.models.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TB_INSTALLMENT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@SequenceGenerator(allocationSize = 1, sequenceName = "sq_installment", name = "installment")
public class InstallmentEntity {

    @Id
    @Column(name = "installment_id", nullable = false, updatable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "installment")
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "installment_maturityDate", nullable = false)
    private Date maturityDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "installment_paymentDate", nullable = false)
    private Date paymentDate;

    @Column(name = "installment_expired", nullable = false)
    private Boolean expired;

    @Column(name = "installment_month", nullable = false)
    private Integer month;

    @Column(name = "installment_amortization", nullable = false)
    private Double amortization;

    @Column(name = "installment_interest", nullable = false)
    private Double interest;

    @Column(name = "installment_value", nullable = false)
    private Double value;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private LoanEntity loan;

}
