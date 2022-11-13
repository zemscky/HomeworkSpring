package com.example.homeworkspring.service;

import com.example.homeworkspring.model.Employee;
import com.example.homeworkspring.record.EmployeeRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final Map<Integer, Employee> employees = new HashMap<>();

    public Collection<Employee> getAllEmployees() {
        return this.employees.values();
    }

    public Employee addEmployee(EmployeeRequest employeeRequest){
        if(employeeRequest.getFirstName() == null
                || employeeRequest.getLastName() == null){
            throw new IllegalArgumentException("Employee name should be set");
        }
        Employee employee = new Employee(
                employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getDepartment(),
                employeeRequest.getSalary());

        this.employees.put(employee.getId(),employee);
        return employee;
    }

    public int getSalarySum() {
        return employees.values().stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public OptionalInt getSalaryMin() {
        return employees.values().stream()
                .mapToInt(Employee::getSalary)
                .min();
    }

    public OptionalInt getSalaryMax() {
        return employees.values().stream()
                .mapToInt(Employee::getSalary)
                .max();
    }

    public Collection<Employee> getSalaryMoreThanAverage() {
        double average = employees.values().stream()
                .mapToInt(Employee::getSalary)
                .average().getAsDouble();
        return employees.values().stream()
                .filter(employee -> employee.getSalary() > average).collect(Collectors.toList());
    }
}
