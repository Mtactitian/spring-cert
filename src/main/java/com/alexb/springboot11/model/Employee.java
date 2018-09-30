package com.alexb.springboot11.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "emp")
@EqualsAndHashCode(of = "id")
@Builder
public class Employee {

    @Id
    @Column(name = "empno",updatable = false)
    private Integer id;

    @Column(name = "ename")
    private String name;

    @Column(name = "job")
    private String job;

    @Column(name = "mgr")
    private Integer managerId;

    @Column(name = "hiredate")
    private LocalDate hireDate;

    @Column(name = "sal")
    private Double salary;

    @Column(name = "comm")
    private Double commission;

    @ManyToOne
    @JoinColumn(name = "deptno", referencedColumnName = "deptno")
    private Department department;
}
