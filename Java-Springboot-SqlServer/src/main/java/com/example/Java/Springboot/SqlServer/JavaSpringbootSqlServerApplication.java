package com.example.Java.Springboot.SqlServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

@SpringBootApplication
public class JavaSpringbootSqlServerApplication implements CommandLineRunner {

	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args)
	{
		SpringApplication.run(JavaSpringbootSqlServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/*	// Example query
		String sql = "SELECT * FROM Employee";
	//	Integer count = jdbctemp.queryForObject(sql, Integer.class);
		var res = jdbctemp.query(sql, new BeanPropertyRowMapper<>(Employee.class));
		System.out.println("Employee: " + res);

		System.out.println("Employee Details:");
		for (Employee employee : res) {
			System.out.println(employee);  // Uses the toString() method of Employee
		}*/

		Scanner scanner = new Scanner(System.in);
		boolean running = true;

		while (running) {
			System.out.println("\nChoose an operation:");
			System.out.println("1. Insert Employee");
			System.out.println("2. Find All Employees");
			System.out.println("3. Find Employee by ID");
			System.out.println("4. Update Employee");
			System.out.println("5. Delete Employee");
			System.out.println("6. Exit");

			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
				case 1 -> insertEmployee(scanner);
				case 2 -> findAllEmployees();
				case 3 -> findEmployeeById(scanner);
				case 4 -> updateEmployee(scanner);
				case 5 -> deleteEmployee(scanner);
				case 6 -> running = false;
				default -> System.out.println("Invalid choice. Please try again.");
			}
		}
		scanner.close();
	}

	// Insert new employee
	private void insertEmployee(Scanner scanner) {
		System.out.print("Enter Employee ID: ");
		int empId = scanner.nextInt();
		scanner.nextLine(); // Consume newline
		System.out.print("Enter Employee Name: ");
		String empName = scanner.nextLine();
		System.out.print("Enter City: ");
		String city = scanner.nextLine();

		Employee employee = new Employee();
		employee.setEmpid(empId);
		employee.setEmpname(empName);
		employee.setCity(city);

		int rowsInserted = employeeRepository.save(employee);
		if (rowsInserted > 0) {
			System.out.println("Employee inserted successfully!");
		}
	}

	// Find all employees
	private void findAllEmployees() {
		System.out.println("Employees:");
		employeeRepository.findAll().forEach(System.out::println);
	}

	// Find employee by ID
	private void findEmployeeById(Scanner scanner) {
		System.out.print("Enter Employee ID to search: ");
		int empId = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		try {
			Employee employee = employeeRepository.findById(empId);
			System.out.println("Employee found: " + employee);
		} catch (Exception e) {
			System.out.println("Employee not found with ID: " + empId);
		}
	}

	// Update employee details
	private void updateEmployee(Scanner scanner) {
		System.out.print("Enter Employee ID to update: ");
		int empId = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		System.out.print("Enter New Employee Name: ");
		String empName = scanner.nextLine();
		System.out.print("Enter New City: ");
		String city = scanner.nextLine();

		Employee employee = new Employee();
		employee.setEmpid(empId);
		employee.setEmpname(empName);
		employee.setCity(city);

		int rowsUpdated = employeeRepository.update(employee);
		if (rowsUpdated > 0) {
			System.out.println("Employee updated successfully!");
		} else {
			System.out.println("Employee not found with ID: " + empId);
		}
	}

	// Delete employee by ID
	private void deleteEmployee(Scanner scanner) {
		System.out.print("Enter Employee ID to delete: ");
		int empId = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		int rowsDeleted = employeeRepository.deleteById(empId);
		if (rowsDeleted > 0) {
			System.out.println("Employee deleted successfully!");
		} else {
			System.out.println("Employee not found with ID: " + empId);
		}
	}
}
