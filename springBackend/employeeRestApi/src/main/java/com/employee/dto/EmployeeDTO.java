package com.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @NoArgsConstructor
public class EmployeeDTO {
    private Long empNo;
    private Date birthDate;
    private String firstName;
    private String lastName;
    private String gender;
    private Date hireDate;
    private String actualTitle;
    private long actualSalary;
    private Date lastPromotion;

    public EmployeeDTO(Long empNo, Date birthDate, String firstName, String lastName,
                       String gender, Date hireDate, String actualTitle, long actualSalary, Date lastPromotion) {
        this.empNo = empNo;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.hireDate = hireDate;
        this.actualTitle = actualTitle;
        this.actualSalary = actualSalary;
        this.lastPromotion=lastPromotion;

    }

    public EmployeeDTO(String firstName, String lastName, String title){
        this.firstName=firstName;
        this.lastName=lastName;
        this.actualTitle=title;
    }


    public EmployeeDTO(String firstName, String lastName, String actualTitle, Date hireDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hireDate = hireDate;
        this.actualTitle = actualTitle;
    }



    public EmployeeDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public EmployeeDTO(Date hireDate) {
        this.hireDate = hireDate;
    }
}
