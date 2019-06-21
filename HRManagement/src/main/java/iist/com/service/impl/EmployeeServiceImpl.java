package iist.com.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import iist.com.dao.EmployeeDao;
import iist.com.dto.EmployeeDto;
import iist.com.dto.ResponseDto;
import iist.com.entity.EmployeeEntity;
import iist.com.service.EmployeeService;
import iist.com.utils.DateUtils;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao employeeDao;
	
	@Override
	public ResponseDto<EmployeeDto> findById(int id) {
		EmployeeEntity entity = employeeDao.findById(id);
		EmployeeDto dto = new EmployeeDto();
		BeanUtils.copyProperties(entity, dto);
		dto.setPositionId(entity.getPosition().getPositionId());
		dto.setBirthday((DateUtils.convertDateToString(entity.getBirthday())));
		ResponseDto<EmployeeDto> response = new ResponseDto<EmployeeDto>();
		response.setContent(dto);
		response.setMessage(HttpStatus.OK.name());
		return response;
	}

	@Override
	public ResponseDto<EmployeeDto> addEmployee(EmployeeDto dto) {
		EmployeeEntity entity = new EmployeeEntity();
		BeanUtils.copyProperties(dto, entity);
//		entity.setCreateBy(createBy);
		return null;
	}

}
