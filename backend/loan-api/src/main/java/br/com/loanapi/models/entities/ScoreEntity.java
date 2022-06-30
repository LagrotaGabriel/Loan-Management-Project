package br.com.loanapi.models.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TB_SCORE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@SequenceGenerator(allocationSize = 1, sequenceName = "sq_score", name = "score")
public class ScoreEntity {

    @Id
    @Column(name = "score_id", nullable = false, updatable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "score")
    private Long id;

    @Column(name = "customer_name", nullable = false)
    private Double pontuation;

    @OneToOne(mappedBy = "score")
    private CustomerEntity customer;
}
