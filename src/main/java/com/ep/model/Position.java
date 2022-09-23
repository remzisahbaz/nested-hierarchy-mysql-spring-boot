package com.ep.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "positions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Position implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String positionName;

    @OneToMany (cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    @JsonIgnore
    private Set<Assigment> assigments=new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "position_id",referencedColumnName = "id")
    private List<Job> jobs;

    @ManyToOne
    private WorkGroup workGroup;
}
