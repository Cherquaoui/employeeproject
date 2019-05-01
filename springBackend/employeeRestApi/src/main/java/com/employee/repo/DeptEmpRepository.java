package com.employee.repo;

import com.employee.entitiy.DeptEmp;
import com.employee.entitiy.DeptManagerID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptEmpRepository extends JpaRepository<DeptEmp, DeptManagerID> {
}
