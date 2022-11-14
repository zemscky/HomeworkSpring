package com.example.homeworkspring.service;

import com.example.homeworkspring.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final Map<Integer, Employee> employees = new HashMap<>();

    public Collection<Employee> getAllEmployees() {
        return this.employees.values();
    }

    public Employee addEmployee(Employee employee){
        if(employee.getFirstName() == null
                || employee.getLastName() == null){
            throw new IllegalArgumentException("Employee name should be set");
        }

        this.employees.put(employee.getId(),employee);
        return employee;
    }

    public int getSalarySum() {
        return employees.values().stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public Collection<Employee> getSalaryMin() {
        double minBySalary = employees
                .values()
                .stream()
                .mapToInt(Employee::getSalary)
                .min()
                .getAsInt();
        return employees
                .values()
                .stream()
                .filter(e -> e.getSalary() == minBySalary)
                .toList();
    }

    public Collection<Employee> getSalaryMax() {
        double maxBySalary = employees
                .values()
                .stream()
                .mapToInt(Employee::getSalary)
                .max()
                .getAsInt();
        return employees
                .values()
                .stream()
                .filter(e -> e.getSalary() == maxBySalary)
                .toList();
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
