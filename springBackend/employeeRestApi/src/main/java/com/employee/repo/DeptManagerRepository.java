package com.employee.repo;

import com.employee.entitiy.DeptManager;
import com.employee.entitiy.DeptManagerID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface DeptManagerRepository extends JpaRepository<DeptManager, DeptManagerID> {
}
