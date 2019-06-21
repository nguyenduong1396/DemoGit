package iist.com.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import iist.com.dto.EmployeeDto;
import iist.com.dto.ResponseDto;
import iist.com.service.EmployeeService;


@RestController
@RequestMapping("/test")
public class TestApi {

	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/employee")
	public ResponseEntity<?> test(@RequestParam("id") int id){
		ResponseDto<EmployeeDto> employee = employeeService.findById(id);
		return new ResponseEntity<>(employee,HttpStatus.OK);
		
	}
	
	@PostMapping("/employee/add")
	public ResponseEntity<?> addEmployee(@Valid @RequestBody EmployeeDto dto){
		
		return null;
	}
}
