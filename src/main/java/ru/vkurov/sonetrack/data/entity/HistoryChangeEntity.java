package ru.vkurov.sonetrack.data.entity;

import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.vkurov.sonetrack.common.ProblemStage;
import ru.vkurov.sonetrack.common.ProblemStatus;

@Entity
@Table(name = "history_change")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class HistoryChangeEntity {
    @Id
    @SequenceGenerator(name = "history_change_id_seq", sequenceName = "history_change_id_seq",
            allocationSize = 1, initialValue = 50)
    @GeneratedValue(generator = "history_change_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "old_name")
    private String oldName;

    @Column(name = "new_name")
    private String newName;

    @Column(name = "old_status")
    @Enumerated(EnumType.ORDINAL)
    private ProblemStatus oldStatus;

    @Column(name = "new_status")
    @Enumerated(EnumType.ORDINAL)
    private ProblemStatus newStatus;

    @ManyToOne
    @JoinColumn(name = "old_executor")
    private UserEntity oldExecutor;

    @Column(name = "old_stage")
    private ProblemStage oldStage;

    @Column(name = "new_stage")
    private ProblemStage newStage;

    @ManyToOne
    @JoinColumn(name = "new_executor")
    private UserEntity newExecutor;
}
