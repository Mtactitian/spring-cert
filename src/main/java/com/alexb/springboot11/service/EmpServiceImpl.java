package com.alexb.springboot11.service;

import com.alexb.springboot11.dao.EmployeeDao;
import com.alexb.springboot11.dto.EmployeeDto;
import com.alexb.springboot11.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmpServiceImpl implements EmpService {

    private final EmployeeDao employeeDao;

    @Override
    @Transactional
    public void saveEmployee(EmployeeDto employeeDto) {
        employeeDao.saveEmployee(employeeDto);
    }

    @Override
    public EmployeeDto getEmployee(Integer id) {
        Employee employee = employeeDao.getEmployee(id);
        return EmployeeDto.builder()
            .name(employee.getName())
            .deptId(employee.getDepartment().getId())
            .id(employee.getId())
            .commission(employee.getCommission())
            .hireDate(employee.getHireDate())
            .job(employee.getJob())
            .salary(employee.getSalary())
            .managerId(employee.getManagerId())
            .build();
    }

    @Override
    public void updateEmployee(EmployeeDto employeeDto) {
        employeeDao.updateEmployee(employeeDto);
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeDao.deleteEmployee(id);
    }
}
