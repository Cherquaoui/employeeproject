package com.employee.controller;

import com.employee.entitiy.Title;
import com.employee.repo.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TitleController {
    @Autowired
    private TitleRepository titleRepository;
    @GetMapping("/title/{id}")
    public Title getLastTitleByEmployeeId(@PathVariable("id") Long id){
        return titleRepository.findFirstByEmployee_EmpNoOrderByToDateDesc(id);
    }
}
