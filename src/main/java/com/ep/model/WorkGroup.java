package com.ep.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "workGroups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@ToString
public class WorkGroup implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String WorkGroupName;

    @ManyToOne
    private WorkGroupType workGroupType;

    @OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn (name = "work_group_id", referencedColumnName = "id")
    @JsonIgnore
    private List<Position> positions = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "work_group_parent_Id")
    private WorkGroup workGroupParent;

    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    @JoinColumn(name = "work_group_id")
    @JsonIgnore
    private List<WorkGroup> workGroups = new ArrayList<>();

}
