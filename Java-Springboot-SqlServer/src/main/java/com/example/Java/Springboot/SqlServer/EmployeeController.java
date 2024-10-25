package com.example.Java.Springboot.SqlServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return employeeRepository.findById(id);
    }

    @PostMapping
    public String createEmployee(@RequestBody Employee employee) {
        int result = employeeRepository.save(employee);
        return result > 0 ? "Employee created successfully!" : "Error creating employee.";
    }

    @PutMapping("/{id}")
    public String updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        employee.setEmpid(id);
        int result = employeeRepository.update(employee);
        return result > 0 ? "Employee updated successfully!" : "Employee not found.";
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id) {
        int result = employeeRepository.deleteById(id);
        return result > 0 ? "Employee deleted successfully!" : "Employee not found.";
    }
}
