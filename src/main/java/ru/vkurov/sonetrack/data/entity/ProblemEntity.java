package ru.vkurov.sonetrack.data.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.vkurov.sonetrack.common.ProblemStage;
import ru.vkurov.sonetrack.common.ProblemStatus;
import ru.vkurov.sonetrack.common.SourceType;

@Entity
@Table(name = "problem")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@IdClass(ProblemId.class)
public class ProblemEntity {
    @Id
    @SequenceGenerator(name = "problem_id_seq", sequenceName = "problem_id_seq",
            allocationSize = 1, initialValue = 50)
    @GeneratedValue(generator = "problem_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Id
    @Column(name = "prefix")
    private String prefix;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.ORDINAL)
    private SourceType sourceType;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "author")
    private UserEntity author;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private ProblemStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "executor")
    private UserEntity executor;

    @OneToOne
    @JoinColumn(name = "request")
    private RequestEntity request;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "problem")
    @OrderBy(value = "operationDate")
    private List<HistoryEntity> history = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "problem")
    @OrderBy(value = "created")
    private List<CommentEntity> comments = new ArrayList<>();

    @Column(name = "stage")
    @Enumerated(EnumType.ORDINAL)
    private ProblemStage stage;
}
