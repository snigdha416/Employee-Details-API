package com.example.Employee_Details_API.repo;

import com.example.Employee_Details_API.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

//    Optional<Employee> findByEmail(String email);
//
//    // Find by first name containing (case-insensitive)
//    List<Employee> findByFirstnameContainingIgnoreCase(String firstName);
//
//    // Find by last name containing (case-insensitive)
//    List<Employee> findByLastnameContainingIgnoreCase(String lastName);
//
//    // Custom query example
//    @Query("SELECT e FROM Employee e WHERE e.mobile = :phone")
//    Optional<Employee> findByPhoneNumber(@Param("phone") String phone);
//
//    // Check if email exists
//    boolean existsByEmail(String email);
//
//    // Count by some condition
//    long countByAddressContaining(String cityOrArea);
//
//    // Delete by email
//    void deleteByEmail(String email);

    List<Employee> findByFirstnameOrAddress(String firstname, String address);

    Page<Employee> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String keyword, String keyword1, Pageable pageable);
}
