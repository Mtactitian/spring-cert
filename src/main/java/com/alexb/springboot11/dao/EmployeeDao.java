package com.alexb.springboot11.dao;

import com.alexb.springboot11.dto.EmployeeDto;
import com.alexb.springboot11.model.Employee;

public interface EmployeeDao {
    void saveEmployee(EmployeeDto employeeDto);

    Employee getEmployee(Integer id);

    void updateEmployee(EmployeeDto employeeDto);

    void deleteEmployee(Integer id);

}
