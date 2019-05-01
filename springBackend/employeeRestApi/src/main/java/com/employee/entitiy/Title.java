package com.employee.entitiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Table(name = "titles")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "Title.getmytitles", query = "select t, e,s  from Title t " +
        "INNER JOIN t.employee e INNER JOIN t.employee.salaries s")
public class Title {
    @EmbeddedId
    private TitleID titleID;
    private Date toDate;
    @JsonIgnore
    @MapsId("empNo")
    @ManyToOne
    @JoinColumn(name = "emp_no")
    private Employee employee;

    public TitleID getTitleID() {
        return titleID;
    }

    public void setTitleID(TitleID titleID) {
        this.titleID = titleID;
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
