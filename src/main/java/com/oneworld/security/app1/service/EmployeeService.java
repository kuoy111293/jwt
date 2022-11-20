package com.oneworld.security.app1.service;

import com.oneworld.security.app1.models.Employee;
import com.oneworld.security.app1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class EmployeeService implements EmployeeServiceImpl{
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public Employee saveEmployee(Employee empl) {
        empl.setCreatedAt(LocalDateTime.now());
        return employeeRepository.save(empl);
    }
    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }
    @Override
    @Transactional
    public void updateEmployee(Employee employee, String status) {
        employeeRepository.findById(employee.getId())
                .ifPresent(empl1 -> {
                    empl1.setStatus(status);
                    empl1.setUserAccessId(employee.getUserAccessId());
                    empl1.setCreatedAt(LocalDateTime.now());
                    employeeRepository.save(empl1);
                });
    }
}
