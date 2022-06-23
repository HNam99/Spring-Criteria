package com.example.springdatacriteria.repository;

import com.example.springdatacriteria.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employees,Integer>, EmployeeCustomRepository {

}
