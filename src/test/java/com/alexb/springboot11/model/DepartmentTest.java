package com.alexb.springboot11.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

class DepartmentTest {

    @Test
    @DisplayName(value = "Department equals and hash code test")
    public void equalsHashCodeTest() {
        Department department = Department.builder()
                .id(1)
                .build();
        Department anotherDepartment = Department.builder()
                .id(10)
                .build();

        assertThatCode(() -> EqualsVerifier.forClass(Department.class)
                .withOnlyTheseFields("id")
                .withPrefabValues(Department.class, department, anotherDepartment)
                .verify())
                .doesNotThrowAnyException();
    }
}