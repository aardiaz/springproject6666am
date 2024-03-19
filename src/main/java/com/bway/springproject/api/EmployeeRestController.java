package com.bway.springproject.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bway.springproject.model.Employee;
import com.bway.springproject.model.Product;
import com.bway.springproject.repository.ProductRepository;
import com.bway.springproject.service.EmployeeService;

@RestController
public class EmployeeRestController {
	
	@Autowired
	private EmployeeService  empService;
	
	@Autowired
	private ProductRepository  prodRepo;
	
	@GetMapping("/api/emp/list")
	public   List<Employee> getAll() {
		
		return empService.getAllEmpoyees();
	}

    @GetMapping("/api/emp/{id}")
	public  Employee getOne(@PathVariable Long id) {
		
		return empService.getEmployeeById(id);
	}
	
    @PostMapping("/api/emp/add")
    public  String add(@RequestBody Employee  emp) {
    	
    	empService.addEmployee(emp);
    	
    	return "success";
    }
    
    @PutMapping("/api/emp/update")
    public String  update(@RequestBody  Employee emp) {
    	
    	empService.updateEmployee(emp);
    	return "success";
    }
    
    @DeleteMapping("/api/emp/delete/{id}")
    public String delete(@PathVariable  Long id) {
    	
    	empService.deleteEmployee(id);
    	
    	return "success";
    }
	
    
    @GetMapping("/api/emp/j2o")
    public  String  jsonToObject() {
    	
    	RestTemplate  temp = new RestTemplate();
    	Employee emp = temp.getForObject("http://localhost/api/emp/1", Employee.class);
    	
    	return "FirstName = "+emp.getFname();
    }
    
    @GetMapping("/api/emp/ja2oa")
    public  String jsonArrayToObjArray() {
    	
    	RestTemplate temp = new RestTemplate();
    	Employee[]  array = temp.getForObject("http://localhost/api/emp/list", Employee[].class);
    	
    	return "Company = "+array[0].getCompany();
    }
    
    @GetMapping("/api/products")
    public String loadProducts() {
    	
    	RestTemplate temp = new RestTemplate();
    	Product[]  products = temp.getForObject("https://fakestoreapi.com/products", Product[].class);
    	
    	prodRepo.saveAll(List.of(products));
    	
    	return "success";
    }
    
}
