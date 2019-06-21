package iist.com.dao;

import iist.com.entity.AccountEntity;

public interface AccountDao {

	public AccountEntity getAccountByUserName(String username); 
	
}
