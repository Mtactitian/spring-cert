package com.alexb.springboot11.dao;

import com.alexb.springboot11.dao.EmployeeDao;
import com.alexb.springboot11.dto.EmployeeDto;
import com.alexb.springboot11.model.Department;
import com.alexb.springboot11.model.Employee;
import com.alexb.springboot11.util.DataSourceBeanPostProcessor;
import net.ttddyy.dsproxy.asserts.ProxyTestDataSource;
import net.ttddyy.dsproxy.asserts.assertj.DataSourceAssertAssertions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@ExtendWith(SpringExtension.class)
@DataJpaTest(includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = DataSourceBeanPostProcessor.class),
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class)})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("classpath:setup_db.sql")
class EmployeeDaoTest {
    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    ProxyTestDataSource proxyTestDataSource;
    @Autowired
    EmployeeDao employeeDao;

    @BeforeEach
    void dataBaseSetup() {
        proxyTestDataSource.reset();
    }

    @Test
    void saveEmployee() {
        EmployeeDto employeeDto = EmployeeDto.builder().id(13).name("ALEX").deptId(10).build();
        Assertions.assertAll(
                () -> assertThatCode(() -> employeeDao.saveEmployee(employeeDto)).doesNotThrowAnyException(),
                () -> DataSourceAssertAssertions.assertThat(proxyTestDataSource).hasTotalQueryCount(2));
    }

    @Test
    void getEmployee() {
        Department department = testEntityManager.find(Department.class, 10);
        testEntityManager.persistAndFlush(Employee.builder().id(13).department(department).build());
        testEntityManager.clear();
        proxyTestDataSource.reset();
        Employee employee = employeeDao.getEmployee(13);
        Assertions.assertAll(
                () -> assertThat(employee).describedAs("Employee must not be null").isNotNull()
                        .has(new Condition<>(emp -> emp.getId() == 13, "Employee id must be 13")),
                () -> DataSourceAssertAssertions.assertThat(proxyTestDataSource).hasTotalQueryCount(1));
    }

    @Test
    void updateEmployee() {
        Department department = testEntityManager.find(Department.class, 10);
        testEntityManager.persistAndFlush(Employee.builder().id(13).department(department).build());
        testEntityManager.clear();
        proxyTestDataSource.reset();
        employeeDao.updateEmployee(EmployeeDto.builder().id(13).deptId(10).name("ALEX").build());
        testEntityManager.flush();
        testEntityManager.clear();
        Employee employee = testEntityManager.find(Employee.class, 13);
        Assertions.assertAll(
                () -> assertThat(employee).hasFieldOrPropertyWithValue("name", "ALEX"),
                () -> DataSourceAssertAssertions.assertThat(proxyTestDataSource).hasUpdateCount(1));
    }

    @Test
    void deleteEmployee() {
        Department department = testEntityManager.find(Department.class, 10);
        testEntityManager.persistAndFlush(Employee.builder().id(13).department(department).build());
        testEntityManager.clear();
        employeeDao.deleteEmployee(13);
        testEntityManager.flush();
        Assertions.assertAll(
                () -> DataSourceAssertAssertions.assertThat(proxyTestDataSource).hasDeleteCount(1));
    }
}