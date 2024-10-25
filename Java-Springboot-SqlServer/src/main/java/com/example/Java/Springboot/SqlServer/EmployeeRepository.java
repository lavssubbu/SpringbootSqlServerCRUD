package com.example.Java.Springboot.SqlServer;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Repository
public class EmployeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Create
    public int save(Employee emp) {
        String sql = "INSERT INTO Employee VALUES (?, ?,?)";
        return jdbcTemplate.update(sql, emp.getEmpid(), emp.getEmpname(),emp.getCity());
    }

    // Read all users
    public List<Employee> findAll() {
        String sql = "SELECT * FROM Employee";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class));
    }

    // Read user by ID
    public Employee findById(int id) {
        String sql = "SELECT * FROM Employee WHERE Empid = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Employee.class), id);
    }
    // Update
    public int update(Employee user) {
        String sql = "UPDATE Employee SET Empname = ?, city = ? WHERE Empid = ?";
        return jdbcTemplate.update(sql, user.getEmpname(), user.getCity(), user.getEmpid());
    }
    // Delete
    public int deleteById(int id) {
        String sql = "DELETE FROM Employee WHERE Empid = ?";
        return jdbcTemplate.update(sql, id);
    }
}
