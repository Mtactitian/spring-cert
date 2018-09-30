package com.alexb.springboot11.dao;

import com.alexb.springboot11.dto.EmployeeDto;
import com.alexb.springboot11.model.Department;
import com.alexb.springboot11.model.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveEmployee(EmployeeDto employeeDto) {
        Department department = entityManager.find(Department.class, employeeDto.getDeptId());
        Employee employee = Employee.builder()
                .id(employeeDto.getId())
                .name(employeeDto.getName())
                .department(department)
                .commission(employeeDto.getCommission())
                .salary(employeeDto.getSalary())
                .hireDate(employeeDto.getHireDate())
                .job(employeeDto.getJob())
                .managerId(employeeDto.getManagerId())
                .build();

        department.getEmployees().add(employee);
        entityManager.persist(employee);
    }

    @Override
    public Employee getEmployee(Integer id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public void updateEmployee(EmployeeDto employeeDto) {
        Employee employee = entityManager.find(Employee.class, employeeDto.getId());
        Department department = entityManager.find(Department.class, employeeDto.getDeptId());
        department.getEmployees().add(employee);
        employee.setName(employeeDto.getName());
        employee.setCommission(employeeDto.getCommission());
        employee.setSalary(employeeDto.getSalary());
        employee.setHireDate(employeeDto.getHireDate());
        employee.setJob(employeeDto.getJob());
        employee.setManagerId(employeeDto.getManagerId());
        employee.setDepartment(department);
    }

    @Override
    public void deleteEmployee(Integer id) {
        Employee employee = entityManager.find(Employee.class, id);
        Department department = employee.getDepartment();
        department.getEmployees().remove(employee);
    }
}
