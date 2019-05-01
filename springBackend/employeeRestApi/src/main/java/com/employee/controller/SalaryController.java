package com.employee.controller;

import com.employee.entitiy.Salary;
import com.employee.repo.EmployeeRepository;
import com.employee.repo.SalaryRepository;
import com.employee.repo.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SalaryController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private SalaryRepository salaryRepository;
    @Autowired
    private TitleRepository titleRepository;

    @GetMapping("/salaries/{id}")
    public Salary getEmployeeSalaries(@PathVariable("id") Long id){
        return salaryRepository.findFirstByEmployee_EmpNoOrderBySalary(id);
    }
}
