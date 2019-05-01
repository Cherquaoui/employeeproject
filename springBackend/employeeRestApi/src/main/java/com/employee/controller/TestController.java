package com.employee.controller;

import com.employee.dto.EmployeeDTO;
import com.employee.hibernate.CriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private CriteriaService criteriaService;


    @GetMapping("criteria")
    public Page<EmployeeDTO> getEmployeesCriteria(@RequestParam(name = "page", defaultValue = "0") int page,
                                                  @RequestParam(name = "size", defaultValue = "10") int size,
                                                  @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                  @RequestParam(name = "term", defaultValue = "empNo") String term
            , @RequestParam(name = "search", defaultValue = "") String search) {
        return criteriaService.getEmployeesCriteria(PageRequest.of(page,size, Sort.Direction.fromString(direction),term),search);
    }


}
