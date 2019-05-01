package com.employee.entitiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Table(name = "dept_manager")
@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class DeptManager implements Serializable {
    @EmbeddedId
    private DeptManagerID deptManagerID;
    private Date fromDate;
    private Date toDate;
    @JsonIgnore
    @MapsId("empNo")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_no")
    private Employee employee;
}
