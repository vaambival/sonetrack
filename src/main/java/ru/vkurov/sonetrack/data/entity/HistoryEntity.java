package ru.vkurov.sonetrack.data.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "history")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class HistoryEntity {
    @Id
    @SequenceGenerator(name = "history_id_seq", sequenceName = "history_id_seq",
            allocationSize = 1, initialValue = 50)
    @GeneratedValue(generator = "history_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumns(value = {
            @JoinColumn(name = "problem_id", referencedColumnName = "id"),
            @JoinColumn(name = "problem_prefix", referencedColumnName = "prefix")
    })
    private ProblemEntity problem;

    @Column(name = "operation_date")
    private LocalDateTime operationDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "history_changes")
    private HistoryChangeEntity historyChange;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author")
    private UserEntity author;
}
