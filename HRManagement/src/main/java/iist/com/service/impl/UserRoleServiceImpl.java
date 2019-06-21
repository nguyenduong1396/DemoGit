package iist.com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import iist.com.dao.UserRoleDao;
import iist.com.service.UserRoleService;

public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	UserRoleDao userRoleDao;
	
	@Override
	public List<String> getUserRoleByUserId(String userId) {
		return userRoleDao.getUserRoleByUserId(userId);
	}

}
