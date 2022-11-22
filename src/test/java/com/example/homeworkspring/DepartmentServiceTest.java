package com.example.homeworkspring;

import com.example.homeworkspring.model.Employee;
import com.example.homeworkspring.service.DepartmentService;
import com.example.homeworkspring.service.EmployeeService;
import exception.EmployeeNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    private final List<Employee> employees = List.of(
            new Employee("TestOne", "TestOne", 1, 5_000),
            new Employee("TestTwo", "TestTwo", 1, 6_000),
            new Employee("TestThree", "TestThree", 1, 7_000),
            new Employee("TestFour", "TestFour", 2, 8_000),
            new Employee("TestFive", "TestFive", 2, 9_000)
    );

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    void setUp(){
        when(employeeService.getAllEmployees())
                .thenReturn(employees);
    }

    @Test
    void getDepartmentEmployees(){
        Collection<Employee> employeeList = this.departmentService.getDepartmentEmployees(1);
        assertThat(employeeList)
                .hasSize(3)
                .contains(employees.get(0),
                        employees.get(1),
                        employees.get(2));
    }

    @Test
    void getSumOfSalariesInDepartment(){
        int sum = departmentService.getSumOfSalariesByDepartment(1);
        assertThat(sum).isEqualTo(18_000);
    }

    @Test
    void getMaxOfSalariesInDepartment(){
        int max = departmentService.getMaxOfSalariesByDepartment(2);
        assertThat(max).isEqualTo(9_000);
    }

    @Test
    void getMinOfSalariesInDepartment(){
        int min = departmentService.getMinOfSalariesByDepartment(2);
        assertThat(min).isEqualTo(8_000);
    }

    @Test
    void getEmployeesGroupedByDepartment(){
        Map<Integer, List<Employee>> groupedEmployees = this.departmentService.getEmployeesGroupedByDepartment();
        assertThat(groupedEmployees)
                .hasSize(2)
                .containsKey(1)
                .containsKey(2)
                .containsEntry(1, List.of(employees.get(0), employees.get(1), employees.get(2)))
                .containsEntry(2, List.of(employees.get(3), employees.get(4)));

    }

    @Test
    void whenNoEmployeesThenGroupInReturnEmptyMap(){
        when(employeeService.getAllEmployees()).thenReturn(List.of());
        Map<Integer, List<Employee>> groupedEmployees = this.departmentService.getEmployeesGroupedByDepartment();
        assertThat(groupedEmployees).isEmpty();
    }

    @Test
    void whenNotEmployeesThenMaxSalariesInDepartmentThrowsException(){
        when(employeeService.getAllEmployees()).thenReturn(List.of());
        Assertions
                .assertThatThrownBy(() ->
                        departmentService
                                .getMaxOfSalariesByDepartment(0))
                .isInstanceOf(EmployeeNotFoundException.class);
    }

    @Test
    void whenNotEmployeesThenMinSalariesInDepartmentThrowsException(){
        when(employeeService.getAllEmployees()).thenReturn(List.of());
        Assertions
                .assertThatThrownBy(() ->
                        departmentService
                                .getMinOfSalariesByDepartment(0))
                .isInstanceOf(EmployeeNotFoundException.class);
    }

    @Test
    void whenNotEmployeesThenSumSalariesInDepartmentThrowsException(){
        when(employeeService.getAllEmployees()).thenReturn(List.of());
        Assertions
                .assertThatThrownBy(() ->
                        departmentService
                                .getSumOfSalariesByDepartment(0))
                .isInstanceOf(EmployeeNotFoundException.class);
    }
}
