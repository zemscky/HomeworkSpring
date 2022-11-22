package com.example.homeworkspring.controller;

import com.example.homeworkspring.model.Employee;
import com.example.homeworkspring.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public Collection<Employee> getAllEmployees(){
        return this.employeeService.getAllEmployees();
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee){
        return this.employeeService.addEmployee(employee);
    }

    @GetMapping("/employee/salary/sum")
    public int getSalarySum(){
        return employeeService.getSalarySum();
    }

    @GetMapping("/employee/salary/min")
    public Employee getSalaryMin(){
        return employeeService.getSalaryMin();
    }

    @GetMapping("/employee/salary/max")
    public Employee getSalaryMax(){
        return employeeService.getSalaryMax();
    }

    @GetMapping("/employee/high-salary")
    public Collection<Employee> getHighSalaryEmployees(){
        return employeeService.getHighSalaryEmployees();
    }
}

