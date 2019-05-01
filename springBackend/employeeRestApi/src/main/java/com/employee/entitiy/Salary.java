package com.employee.entitiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Table(name = "salaries")
@Entity @Data @AllArgsConstructor @NoArgsConstructor

public class Salary {
    @EmbeddedId
    private SalaryID salaryID;
    private Long salary;
    private Date toDate;
    @MapsId("empNo")
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "emp_no")
    private Employee employee;

    public SalaryID getSalaryID() {
        return salaryID;
    }

    public void setSalaryID(SalaryID salaryID) {
        this.salaryID = salaryID;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
