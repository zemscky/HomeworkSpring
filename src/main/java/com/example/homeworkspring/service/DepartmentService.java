package com.example.homeworkspring.service;

import com.example.homeworkspring.model.Employee;
import exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private Stream<Employee> getEmployeesByDepartmentStream(int department){
        return employeeService
                .getAllEmployees()
                .stream()
                .filter(e -> e.getDepartment() == department);
    }

    public Collection<Employee> getDepartmentEmployees(int department){
        return employeeService
                .getAllEmployees()
                .stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    public int getSumOfSalariesByDepartment(int department){
        return getEmployeesByDepartmentStream(department)
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public int getMaxOfSalariesByDepartment(int department){
        return getEmployeesByDepartmentStream(department)
                .mapToInt(Employee::getSalary)
                .max()
                .orElseThrow(EmployeeNotFoundException:: new);
    }

    public int getMinOfSalariesByDepartment(int department){
        return getEmployeesByDepartmentStream(department)
                .mapToInt(Employee::getSalary)
                .min()
                .orElseThrow(EmployeeNotFoundException :: new);
    }

    public Map<Integer, List<Employee>> getEmployeesGroupedByDepartment(){
        return employeeService.getAllEmployees()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

}
