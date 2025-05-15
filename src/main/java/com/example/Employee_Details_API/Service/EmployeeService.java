package com.example.Employee_Details_API.Service;

import com.example.Employee_Details_API.model.Employee;
import com.example.Employee_Details_API.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    public List<Employee> getAllEmployDeails() {
        return (List<Employee>) employeeRepo.findAll();
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public Optional<Employee> getEmployee(Integer id) {
        return employeeRepo.findById(id);
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public void deleteEmployee(Integer id) {
       employeeRepo.deleteById(id);
    }

    public List<Employee> searchKey(String keyword) {
        return employeeRepo.findByFirstnameOrAddress(keyword, keyword);
    }

    public Page<Employee> searchEmployees(String keyword, Pageable pageable) {

        return employeeRepo.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
                keyword, keyword, pageable);
    }
}
