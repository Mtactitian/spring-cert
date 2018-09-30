package com.alexb.springboot11.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

class EmployeeTest {

    @Test
    @DisplayName("Employee equals and hash code test")
    public void equalsHashCodeTest() {

        Employee employee = Employee.builder()
                .id(1)
                .build();
        Employee anotherEmployee = Employee.builder()
                .id(10)
                .build();

        assertThatCode(() ->
                EqualsVerifier.forClass(Employee.class)
                        .withOnlyTheseFields("id")
                        .withPrefabValues(Employee.class, employee, anotherEmployee)
                        .verify())
                .doesNotThrowAnyException();
    }
}