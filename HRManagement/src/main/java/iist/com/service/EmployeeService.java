package iist.com.service;


import iist.com.dto.EmployeeDto;
import iist.com.dto.ResponseDto;


public interface EmployeeService {

	public ResponseDto<EmployeeDto> findById(int id);
	
	public ResponseDto<EmployeeDto> addEmployee(EmployeeDto dto);
}
