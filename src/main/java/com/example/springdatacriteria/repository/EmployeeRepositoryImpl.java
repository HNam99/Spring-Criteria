package com.example.springdatacriteria.repository;

import com.example.springdatacriteria.model.Employees;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeCustomRepository {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Employees> findByFirstNameAndDepartment(String firstName, String department) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employees> criteriaQuery = criteriaBuilder.createQuery(Employees.class);
        Root<Employees> employee = criteriaQuery.from(Employees.class);

        Predicate firstNamePredicate = criteriaBuilder.equal(employee.get("firstName"), firstName);
        Predicate departmentPredicate = criteriaBuilder.equal(employee.get("department"), department);

        criteriaQuery.where(firstNamePredicate, departmentPredicate);
        TypedQuery<Employees> query = entityManager.createQuery(criteriaQuery);
        //SELECT * FROM employees.employees
        // WHERE employees.first_name = 'firstName'
        // AND department = 'department'
        return query.getResultList();
    }

    @Override
    @Transactional
    public void deleteEmployeeByFirstName(String firstName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Employees> delete = criteriaBuilder.createCriteriaDelete(Employees.class);

        Root<Employees> employeesRoot = delete.from(Employees.class);

        delete.where(criteriaBuilder.lessThanOrEqualTo(employeesRoot.get("firstName"), firstName));
        entityManager.createQuery(delete).executeUpdate();
    }
}
