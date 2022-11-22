package com.example.homeworkspring;

import com.example.homeworkspring.model.Employee;
import com.example.homeworkspring.service.EmployeeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeServiceTest {

    private EmployeeService employeeService;

    @BeforeEach
    public void setUp(){
        this.employeeService = new EmployeeService();
        Stream.of(
                new Employee("TestOne", "TestOne", 1, 10_000),
                new Employee("TestTwo", "TestTwo", 2, 10_000),
                new Employee("TestThree", "TestThree", 3, 15_000),
                new Employee("TestFour", "TestFour", 4, 10_000),
                new Employee("TestFive", "TestFive", 4, 20_000)
        ).forEach(employeeService::addEmployee);
    }

    @Test
    public void addEmployee(){
        Employee request = new Employee(
                "Valid", "Valid", 3, 10_000);
        Employee result = employeeService.addEmployee(request);
        assertEquals(request.getFirstName(), result.getFirstName());
        assertEquals(request.getLastName(), result.getLastName());
        assertEquals(request.getSalary(), result.getSalary());
        assertEquals(request.getDepartment(), result.getDepartment());

        Assertions
                .assertThat(employeeService.getAllEmployees())
                .contains(result);
    }

    @Test
    public void listEmployees(){
        Collection<Employee> employees = employeeService.getAllEmployees();
        Assertions.assertThat(employees).hasSize(5);
        Assertions.assertThat(employees)
                .first()
                .extracting(Employee::getFirstName)
                .isEqualTo("TestOne");
    }

    @Test
    public void sumOfSalaries(){
        int sum = employeeService.getSalarySum();
        Assertions.assertThat(sum)
                .isEqualTo(65_000);
    }

    @Test
    public void minOfSalaries(){
        Employee employee = employeeService.getSalaryMin();
        Assertions.assertThat(employee)
                .isNotNull()
                .extracting(Employee::getFirstName)
                .isEqualTo("TestOne");
    }

    @Test
    public void maxOfSalaries(){
        Employee employee = employeeService.getSalaryMax();
        Assertions.assertThat(employee)
                .isNotNull()
                .extracting(Employee::getFirstName)
                .isEqualTo("TestFive");
    }

    @Test
    public void averageOfSalaries(){
        double averageSalary = employeeService.getSalaryAverage();
        Assertions.assertThat(averageSalary)
                .isNotNull()
                .isEqualTo(13_000);
    }

    @Test
    public void getHighSalaryEmployees(){
        Collection<Employee> HighSalaryEmployees = employeeService.getHighSalaryEmployees();
        Assertions.assertThat(HighSalaryEmployees).hasSize(2);
    }

    @Test
    public void deleteEmployee(){
        employeeService.deleteEmployee(0);
        Collection<Employee> employees = employeeService.getAllEmployees();

        Assertions.assertThat(employees).hasSize(4);
    }

}

