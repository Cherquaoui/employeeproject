package com.employee.repo;

import com.employee.dto.EmployeeDTO;
import com.employee.entitiy.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

    @Query("SELECT " +
            "e FROM Employee e " +
            "WHERE " +
            "e.firstName like %:x% " +
            "OR " +
            "e.lastName like %:x% "
    )
    Page<Employee> findByName(String x, Pageable p);


    @Query(value = "SELECT new com.employee.dto.EmployeeDTO(e.firstName,e.lastName,t.titleID.title, max(t.titleID.fromDate) )   " +
            "from Employee e " +
            "INNER JOIN e.titles t " +
            " WHERE e.firstName like concat('%',:search,'%') " +
            "or e.lastName like concat('%',:search,'%') " +
            "or t.titleID.title like concat('%',:search,'%') " +
            "GROUP BY e.empNo "/*, countQuery = "SELECT count(e)    " +
            "from Employee e " +
            "INNER JOIN e.titles t " +
            " WHERE e.firstName like concat('%',:search,'%') " +
            "or e.lastName like concat('%',:search,'%') " +
            "or t.titleID.title like concat('%',:search,'%') " +
            "GROUP BY e.empNo"*/)
    Page<EmployeeDTO> getmyemployees(@Param("search") String search,  Pageable p);
    @Query(value = "SELECT e.first_name, t.title   " +
            "from employees e " +
            "INNER JOIN titles t on e.emp_no = t.emp_no " +
            " WHERE e.first_name like concat('%',:search,'%') " +
            "or e.last_name like concat('%',:search,'%') " +
            "or t.title like concat('%',:search,'%') " +
            "GROUP BY e.emp_no ORDER BY ':term',':direction'",countQuery = "SELECT count(1)   " +
            "from employees e "
            ,nativeQuery = true)
    Page<Object> getmyemployeesNative(@Param("search") String search,@Param("term") String term,
                                      @Param("direction") String direction , Pageable p);

}
