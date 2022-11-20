package com.oneworld.security.app1.controller;

import com.oneworld.security.app1.models.Employee;
import com.oneworld.security.app1.models.User;
import com.oneworld.security.app1.repository.UserRepository;
import com.oneworld.security.app1.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeServiceImpl employeeService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee)
    {
        return new ResponseEntity<> (
                employeeService.saveEmployee(employee),
                HttpStatus.CREATED
        );
    }
    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> geEmployee()
    {
        return new ResponseEntity<>(
                employeeService.getAllEmployee(),
                HttpStatus.OK
        );
    }
    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> editEmployee(@Param("id") Long id,
                                          @Param("status") String status,
                                          @Param("userId") Long userId)
    {
        Employee employee = new Employee();
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty())
        {
            return new ResponseEntity<>("Cannot find user provided!", HttpStatus.FORBIDDEN);
        }
        employee.setId(id);
        employee.setUserAccessId(userId);
        employeeService.updateEmployee(employee, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
