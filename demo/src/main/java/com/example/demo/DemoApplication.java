package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@SpringBootApplication
@RestController
@RefreshScope
public class DemoApplication {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Value("${spring.datasource.username}")
	public String dbusername;

	@Value("${spring.datasource.password}")
	public String dbpassword;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/vault")
	public String getVaultSecret() {
		return "DB credentials from vault \n Username : " + dbusername + " \n Password : " + dbpassword;
	}
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
}
