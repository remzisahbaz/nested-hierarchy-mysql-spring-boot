package com.ep.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity (name = "workGroupTypes")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkGroupType implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String workGroupTypeName;

    @OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn (name = "work_group_type_id", referencedColumnName = "id")
    @JsonIgnore
    private List<WorkGroup> workGroups = new ArrayList<>();
}
