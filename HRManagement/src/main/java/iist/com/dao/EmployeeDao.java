package iist.com.dao;

import iist.com.entity.EmployeeEntity;

public interface EmployeeDao {

	public EmployeeEntity findById(int id);
	
	public void addEmployee(EmployeeEntity entity);
}
