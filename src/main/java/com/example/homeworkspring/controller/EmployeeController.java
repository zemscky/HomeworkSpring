package com.example.homeworkspring.controller;

import com.example.homeworkspring.model.Employee;
import com.example.homeworkspring.record.EmployeeRequest;
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
    public Employee createEmployee(@RequestBody EmployeeRequest employeeRequest){
        return this.employeeService.addEmployee(employeeRequest);
    }

    @GetMapping("/employee/salary/sum")
    public int getSalarySum(){
        return this.employeeService.getSalarySum();
    }

    @GetMapping("/employee/salary/min")
    public Collection<Employee> getSalaryMin(){
        return this.employeeService.getSalaryMin();
    }

    @GetMapping("/employee/salary/max")
    public Employee getSalaryMax(){
        return this.employeeService.getSalaryMax();
    }

    @GetMapping("/employee/high-salary")
    public Collection<Employee> getSalaryMoreThanAverage() {
        return this.employeeService.getSalaryMoreThanAverage();
    }
}
