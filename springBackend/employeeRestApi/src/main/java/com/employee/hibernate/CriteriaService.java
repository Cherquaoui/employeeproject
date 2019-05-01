package com.employee.hibernate;

import com.employee.dto.EmployeeDTO;
import com.employee.entitiy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Service
public class CriteriaService {

    EntityManager em;

    public CriteriaService(EntityManager em) {
        this.em = em;
    }

    public Page<EmployeeDTO> getEmployeesCriteria(Pageable p, String search) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<EmployeeDTO> criteriaQuery = cb.createQuery(EmployeeDTO.class);
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
        Join<Employee, Title> titleJoin = employeeRoot.join(Employee_.titles);
        Join<Employee, Salary> salaryJoin = employeeRoot.join(Employee_.salaries);
        Predicate p1 = cb.like(employeeRoot.get("firstName"), '%' + search + '%');
        Predicate p2 = cb.like(titleJoin.get(Title_.TITLE_ID).get("title"), '%' + search + '%');
        Predicate p3 = cb.like(employeeRoot.get("lastName"), '%' + search + '%');
        Predicate p4 = cb.or(p1, p2, p3);
        criteriaQuery.where(p4);

        criteriaQuery.multiselect(employeeRoot.get(Employee_.empNo),
                employeeRoot.get(Employee_.birthDate),
                employeeRoot.get(Employee_.firstName),
                employeeRoot.get(Employee_.lastName),
                employeeRoot.get(Employee_.gender),
                employeeRoot.get(Employee_.hireDate),
                titleJoin.get(Title_.TITLE_ID).get("title"),
                cb.max(salaryJoin.get(Salary_.salary)),
                cb.max(titleJoin.get(Title_.TITLE_ID).get(TitleID_.FROM_DATE)))
                .groupBy(employeeRoot.get(Employee_.empNo));

        //sorting --------------------------------------------------------
        String s = p.getSort().toString();
        String sortTerm = s.substring(0, s.indexOf(':'));
        String sortDirection = s.substring(s.indexOf(':') + 2);
        System.out.println("-----" + sortTerm + "-----");
        System.out.println("-----" + sortDirection + "-----");

        switch (sortDirection) {
            case "ASC":
                switch (sortTerm) {
                    case "title":
                        criteriaQuery.orderBy(cb.asc(titleJoin.get("titleID").get("title")));
                        break;
                    case "salary":
                        criteriaQuery.orderBy(cb.asc(cb.max(salaryJoin.get(Salary_.salary))));
                        break;
                    default:
                        criteriaQuery.orderBy(cb.asc(employeeRoot.get(sortTerm)));
                }
                break;
            case "DESC":
                switch (sortTerm) {
                    case "title":
                        criteriaQuery.orderBy(cb.desc(titleJoin.get("titleID").get("title")));
                        break;
                    case "salary":
                        criteriaQuery.orderBy(cb.desc(cb.max(salaryJoin.get(Salary_.salary))));
                        break;
                    default:
                        criteriaQuery.orderBy(cb.desc(employeeRoot.get(sortTerm)));
                }
                break;
        }

        //Sorting end -------------------------------------------------------

        TypedQuery<EmployeeDTO> e = em.createQuery(criteriaQuery);
        List<EmployeeDTO> results = e.setFirstResult((int) p.getOffset())
                .setMaxResults(p.getPageSize())
                .getResultList();

        // cout query --------------------------------------------------
        CriteriaQuery<Long> count = cb.createQuery(Long.class);

        Root<Employee> countRoot = count.from(Employee.class);
        count.select(cb.count(countRoot.get(Employee_.EMP_NO)));
        count.where(cb.like(countRoot.get("firstName"), '%' + search + '%'));
        Long total = em.createQuery(count).getSingleResult();
        return new PageImpl<>(results, p, total);

    }

}
