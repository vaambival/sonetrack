package ru.vkurov.sonetrack.data.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "comment")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class CommentEntity {
    @Id
    @SequenceGenerator(name = "comment_id_seq", sequenceName = "comment_id_seq",
        allocationSize = 1, initialValue = 50)
    @GeneratedValue(generator = "comment_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author")
    private UserEntity author;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns(value = {
        @JoinColumn(name = "problem_id", referencedColumnName = "id"),
        @JoinColumn(name = "prefix", referencedColumnName = "prefix")
    })
    private ProblemEntity problem;
}
