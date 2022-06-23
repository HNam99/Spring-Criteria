package com.example.springdatacriteria.repository;

import com.example.springdatacriteria.model.Employees;

import java.util.List;

public interface EmployeeCustomRepository {

    List<Employees> findByFirstNameAndDepartment(String firstName, String department);

    void deleteEmployeeByFirstName(String firstName);
}
