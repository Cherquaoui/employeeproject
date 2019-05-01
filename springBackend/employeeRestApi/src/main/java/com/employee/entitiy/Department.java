package com.employee.entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

@Entity @Table(name = "departments")
@Data @NoArgsConstructor @AllArgsConstructor
public class Department {
    @Id
    private String deptNo;
    private String deptName;

}
