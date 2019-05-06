package ru.vkurov.sonetrack.data.entity;

import java.io.Serializable;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(of = {"id", "prefix"})
@NoArgsConstructor
@AllArgsConstructor
public class ProblemId implements Serializable {
    private Long id;
    private String prefix;
}
