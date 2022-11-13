package com.example.homeworkspring.service;

import com.example.homeworkspring.model.Employee;
import com.example.homeworkspring.record.EmployeeRequest;
import org.springframework.stereotype.Service;

import java.util.*;
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

    public Collection<Employee> getSalaryMin() {
        return Collections.singleton(employees
                .values()
                .stream()
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(NoSuchElementException::new));
    }

    public Employee getSalaryMax() {
        return employees
                .values()
                .stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(NoSuchElementException::new);
    }

    public Collection<Employee> getSalaryMoreThanAverage() {
        double average = employees.values()
                .stream()
                .mapToInt(Employee::getSalary)
                .average()
                .getAsDouble();
        return employees
                .values()
                .stream()
                .filter(employee -> employee.getSalary() > average)
                .collect(Collectors.toList());
    }
}
