package com.example.Employee_Details_API.controller;

import com.example.Employee_Details_API.Service.EmployeeService;
import com.example.Employee_Details_API.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;


import java.util.*;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

//    @GetMapping("/getEmployees")
//    public List<Employee> getAllEmployDetails(){
//        return employeeService.getAllEmployDeails();
//    }
    @GetMapping("/getEmployees")
    public ResponseEntity<Map<String, Object>> getAllEmployDetails(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort) {

        List<Sort.Order> orders = new ArrayList<>();

        if (sort[0].contains(",")) {
            // will sort more than 2 fields
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Sort.Order(Sort.Direction.fromString(_sort[1]), _sort[0]));
            }
        } else {
            // sort=[field, direction]
            orders.add(new Sort.Order(Sort.Direction.fromString(sort[1]), sort[0]));

            System.out.println("git commands");
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));

        Page<Employee> employeePage = employeeService.getAllEmployDetails(pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("employees", employeePage.getContent());
        response.put("currentPage", employeePage.getNumber());
        response.put("totalItems", employeePage.getTotalElements());
        response.put("totalPages", employeePage.getTotalPages());

        return ResponseEntity.ok(response);
    }

    private Sort.Order getSortOrder(String[] sort) {
        if (sort[0].contains(",")) {
            // will sort more than 2 fields
            // sortOrder="field, direction"
            return new Sort.Order(Sort.Direction.fromString(sort[1]), sort[0]);
        } else {
            // sort=[field, direction]
            return new Sort.Order(Sort.Direction.fromString(sort[1]), sort[0]);
        }
    }

    @PostMapping("/addEmployee")
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }

    @GetMapping("/getEmployee/{id}")
    public Optional<Employee> getEmployee(@PathVariable Integer id){
        return employeeService.getEmployee(id);
    }

    @PutMapping("/updateEmployee")
    public Employee updateEmployee(@RequestBody Employee employee){
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/delEmployee/{id}")
    public String deleteEmployee(@PathVariable Integer id){
         employeeService.deleteEmployee(id);
         return "Deleted";
    }

    @GetMapping("/search/{keyword}")
    public List<Employee> searchKey(@PathVariable("keyword") String keyword){
        return employeeService.searchKey(keyword);
    }
}
