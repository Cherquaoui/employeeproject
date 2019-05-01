package com.employee.repo;
import com.employee.entitiy.Title;
import com.employee.entitiy.TitleID;
import javafx.scene.control.TreeItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TitleRepository extends PagingAndSortingRepository<Title, TitleID> {
    Title findFirstByEmployee_EmpNoOrderByToDateDesc(Long id);

    @Query(nativeQuery = true, value = "SELECT " +
            "t.emp_no, t.title,max(s.salary) ,e.first_name, e.last_name, max(t.to_date)" +
            "FROM titles t " +
            " INNER JOIN employees e on t.emp_no = e.emp_no " +
            "INNER JOIN salaries s on t.emp_no = s.emp_no" +
            " WHERE  e.first_name like %:search% or e.last_name like %:search%" +
            " GROUP BY t.emp_no"

            ,
            countQuery = "SELECT " +
                    "e.emp_no " +
                    "FROM " +
                    "employees e " +
                    " INNER JOIN " +
                    "titles t on t.emp_no = e.emp_no " + /*+
                    "INNER JOIN" +
                    " salaries s on e.emp_no = s.emp_no" +*/
                    " where t.title like %:search% or e.first_name like %:search% or e.last_name like %:search%" +
                    " GROUP BY t.emp_no")
    Page<Object> titleTest(@Param("search") String search, Pageable p);

    Page<Object> getmytitles(Pageable p);

}
