package com.employee.entitiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
@Table(name = "dept_emp")
@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class DeptEmp {
    @EmbeddedId
    private DeptManagerID deptManagerID;
    private Date fromDate;
    private Date toDate;
    @JsonIgnore
    @MapsId("empNo")
    @ManyToOne
    @JoinColumn(name = "emp_no")
    private Employee employee;

}
