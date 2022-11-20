package com.oneworld.security.app1.service;

import com.oneworld.security.app1.models.Employee;

import javax.transaction.Transactional;
import java.util.List;

public interface EmployeeServiceImpl {
    Employee saveEmployee(Employee empl);
    List<Employee> getAllEmployee();
    @Transactional
    void updateEmployee(Employee employee, String status);

}
