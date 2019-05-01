package com.employee.repo;

import com.employee.dto.SalaryDTO;
import com.employee.entitiy.Salary;
import com.employee.entitiy.SalaryID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SalaryRepository extends JpaRepository<Salary, SalaryID> {
    List<Salary> getAllByEmployee_EmpNo(Long empNo);
    Salary findFirstByEmployee_EmpNoOrderBySalary(Long id);

    @Query(nativeQuery = true,value = "SELECT s.emp_no, s.salary FROM salaries s ORDER BY")
    Page<Object> getSalaries(Pageable p);
    public static interface testOnly{
        Long getEmp_no();
        Long getSalary();
    }

}
