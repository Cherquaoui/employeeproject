package com.employee.controller;

import com.employee.dto.EmployeeDTO;
import com.employee.entitiy.Employee;
import com.employee.entitiy.Salary;
import com.employee.entitiy.Title;
import com.employee.hibernate.CriteriaService;
import com.employee.repo.EmployeeRepository;
import com.employee.repo.SalaryRepository;
import com.employee.repo.TitleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    SalaryRepository salaryRepository;
    @Autowired
    private TitleRepository titleRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CriteriaService criteriaService;





    @GetMapping("/employees")
    public Page<EmployeeDTO> getEmployeesCriteria(@RequestParam(name = "page", defaultValue = "0") int page,
                                                  @RequestParam(name = "size", defaultValue = "10") int size,
                                                  @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                  @RequestParam(name = "term", defaultValue = "empNo") String term
            , @RequestParam(name = "search", defaultValue = "") String search) {
        return criteriaService.getEmployeesCriteria(PageRequest.of(page,size, Sort.Direction.fromString(direction),term),search);
    }


    @GetMapping("/{id}")
    private Employee getEmployee(@PathVariable("id") Long id) {
        return employeeRepository.findById(id).get();
    }
}
