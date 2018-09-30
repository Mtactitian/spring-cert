package com.alexb.springboot11.service;

import com.alexb.springboot11.dao.EmployeeDao;
import com.alexb.springboot11.dto.EmployeeDto;
import com.alexb.springboot11.model.Department;
import com.alexb.springboot11.model.Employee;
import com.alexb.springboot11.util.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmpServiceTest {

    private EmpService empService;

    @Test
    void saveEmployee(@Mock EmployeeDao employeeDao) {
        EmployeeDto employeeDto = EmployeeDto.builder().name("Orsini").build();
        ArgumentCaptor<EmployeeDto> captor = ArgumentCaptor.forClass(EmployeeDto.class);
        empService = new EmpServiceImpl(employeeDao);

        empService.saveEmployee(employeeDto);
        Mockito.verify(employeeDao).saveEmployee(captor.capture());

        assertThat(captor.getValue())
            .isEqualToComparingFieldByField(employeeDto);
    }

    @Test
    void getEmployee(@Mock EmployeeDao employeeDao) {
        EmployeeDto expected = EmployeeDto.builder().deptId(10).name("Sforta").build();
        when(employeeDao.getEmployee(10)).thenReturn(Employee.builder()
            .department(Department.builder().id(10).build())
            .name("Sforta")
            .build());
        empService = new EmpServiceImpl(employeeDao);

        EmployeeDto actualEmployee = empService.getEmployee(10);

        assertThat(actualEmployee)
            .isEqualToComparingFieldByFieldRecursively(actualEmployee);
    }

    @Test
    void updateEmployee(@Mock EmployeeDao employeeDao) {
        EmployeeDto employeeDto = EmployeeDto.builder().name("Sforta").build();
        ArgumentCaptor<EmployeeDto> captor = ArgumentCaptor.forClass(EmployeeDto.class);
        empService = new EmpServiceImpl(employeeDao);

        empService.updateEmployee(employeeDto);

        Mockito.verify(employeeDao).updateEmployee(captor.capture());

        assertThat(captor.getValue())
            .isEqualToComparingFieldByFieldRecursively(employeeDto);
    }

    @Test
    void deleteEmployee(@Mock EmployeeDao employeeDao) {
        empService = new EmpServiceImpl(employeeDao);
        empService.deleteEmployee(10);

        verify(employeeDao).deleteEmployee(10);
    }
}