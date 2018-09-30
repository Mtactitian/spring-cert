package com.alexb.springboot11.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class EmployeeDto {
    private Integer id;
    private String name;
    private String job;
    private Integer managerId;
    private LocalDate hireDate;
    private Double salary;
    private Double commission;
    private Integer deptId;
}
