package com.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.SqlResultSetMapping;
import java.sql.Date;

@Data @AllArgsConstructor @NoArgsConstructor
public class SalaryDTO {
    private Long empNo;
    private Long salary;
    private Date fromDate;
    private Date toDate;

    public SalaryDTO(Long empNo, Long salary) {
        this.empNo = empNo;
        this.salary = salary;
    }


}
