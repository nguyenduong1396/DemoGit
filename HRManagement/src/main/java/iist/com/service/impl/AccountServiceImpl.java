package iist.com.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import iist.com.dao.AccountDao;
import iist.com.dao.UserRoleDao;
import iist.com.entity.AccountEntity;
import iist.com.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountDao accountDao;
	
	@Autowired
	UserRoleDao userRoleDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	   // get account by username
		AccountEntity entity = accountDao.getAccountByUserName(username);
		if(ObjectUtils.isEmpty(entity)) {
			System.out.println("User not found! " + username);
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}
		// get roles by userID
		List<String> roleNames = userRoleDao.getUserRoleByUserId(entity.getUserId());
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if(roleNames != null) {
			for(String roleName : roleNames) {
				GrantedAuthority authority = new SimpleGrantedAuthority(roleName);
				grantList.add(authority);
			}
		}
		UserDetails  account = new User(entity.getUsername(),entity.getPassword(),grantList);
		return account;
	}

}
