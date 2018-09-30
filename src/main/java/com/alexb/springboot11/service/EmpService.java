package com.alexb.springboot11.service;

import com.alexb.springboot11.dto.EmployeeDto;
import com.alexb.springboot11.model.Employee;

public interface EmpService {
    void saveEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployee(Integer id);

    void updateEmployee(EmployeeDto employeeDto);

    void deleteEmployee(Integer id);
}