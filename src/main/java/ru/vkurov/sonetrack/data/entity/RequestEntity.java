package ru.vkurov.sonetrack.data.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.vkurov.sonetrack.common.RequestStatus;
import ru.vkurov.sonetrack.common.SourceType;

@Entity
@Table(name = "request")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class RequestEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "source")
    @Enumerated(EnumType.ORDINAL)
    private SourceType source;

    @Column(name = "like_count")
    private int likeCount;

    @Column(name = "comment_count")
    private int commentCount;

    @Column(name = "link")
    private String link;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private RequestStatus status;

    @Column(name = "created")
    private LocalDateTime created;
}
