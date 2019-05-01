package com.employee.entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable @Data @NoArgsConstructor @AllArgsConstructor
public class DeptManagerID implements Serializable {

    @ManyToOne
    @JoinColumn(name = "dept_no")
    private Department dept;
    @Column(name = "emp_no")
    private Long empNo;
}
