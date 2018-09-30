package com.alexb.springboot11.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dept")
@EqualsAndHashCode(of = "id")
@Builder
public class Department {

    @Id
    @Column(name = "deptno",updatable = false)
    private Integer id;

    @Column(name = "dname")
    private String name;

    @Column(name = "loc")
    private String location;

    @OneToMany(mappedBy = "department", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Employee> employees = new TreeSet<>();

}
