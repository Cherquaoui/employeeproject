package com.employee.entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Table(name = "employees")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    private Long empNo;
    private Date birthDate;
    private String firstName;
    private String lastName;
    private String gender;
    private Date hireDate;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private Collection<DeptManager> deptManagers;
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private Collection<Title> titles;
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private Collection<DeptEmp> deptEmps;
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private Collection<Salary> salaries;


}
