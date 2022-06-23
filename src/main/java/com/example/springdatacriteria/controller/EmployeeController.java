package com.example.springdatacriteria.controller;

import com.example.springdatacriteria.model.Employees;
import com.example.springdatacriteria.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    public EmployeeController (EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }
    @PostMapping("/employees")
    public Employees createEmployee(@RequestBody Employees employees){
        return employeeRepository.save(employees);
    }

    @GetMapping("/employees")
    public List<Employees> findAllEmployees(){
        return employeeRepository.findAll();

    }

    @GetMapping("/employees/{firstName}/{department}")
    public List<Employees> findByFirstNameAndDepartment(@PathVariable("firstName") String firstName ,
                                                        @PathVariable("department") String department){
       return employeeRepository.findByFirstNameAndDepartment(firstName,department);
    }

    @DeleteMapping("/employees/{firstName}")
    public void deleteEmployeeByFirstName(@PathVariable("firstName") String firstName){
     employeeRepository.deleteEmployeeByFirstName(firstName);
    }

}