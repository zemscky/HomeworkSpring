package com.example.homeworkspring.service;

import com.example.homeworkspring.model.Employee;
import exception.EmployeeNotFoundException;
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

    public int getSalarySum(){
        return employees.values()
                .stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public Employee getSalaryMin() {
        return employees
                .values()
                .stream()
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException :: new);
    }

    public Employee getSalaryMax() {
        return employees
                .values()
                .stream()
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException :: new);
    }

    public double getSalaryAverage(){
        return employees.values().stream()
                .mapToInt(Employee::getSalary)
                .average()
                .orElseThrow(EmployeeNotFoundException :: new);
    }

    public Collection<Employee> getHighSalaryEmployees(){
        Double averageSalary = getSalaryAverage();

        return employees.values().stream()
                .filter(e-> e.getSalary() >= averageSalary)
                .collect(Collectors.toList());

    }

    public void deleteEmployee(int id){
        employees.remove(id);
    }
}
