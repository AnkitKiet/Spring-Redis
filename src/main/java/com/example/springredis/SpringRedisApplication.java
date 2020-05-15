package com.example.springredis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringRedisApplication {

	@Autowired
	EmployeeRepo employeeRepo;

	public static void main(String[] args) {
		SpringApplication.run(SpringRedisApplication.class, args);
	}

	@GetMapping("/getData")
	public List<Employee> getData() {
		return (List<Employee>) employeeRepo.findAll();
	}

	@GetMapping("/saveData")
	public boolean saveData(@RequestParam String name, @RequestParam String address) {
		try {
			Employee employee = new Employee(name.hashCode(),name, address);
			employeeRepo.save(employee);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
