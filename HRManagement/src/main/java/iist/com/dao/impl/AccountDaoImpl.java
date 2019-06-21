package iist.com.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import iist.com.dao.AccountDao;
import iist.com.entity.AccountEntity;

@Repository
@Transactional
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private SessionFactory sessionFactory;
    
	@Override
	public AccountEntity getAccountByUserName(String username) {
		Session session = this.sessionFactory.getCurrentSession();
		String sql = "Select a from AccountEntity a where a.username = :username";
		Query<AccountEntity> query = session.createQuery(sql, AccountEntity.class);
		query.setParameter("username", username);
		AccountEntity entity = query.getSingleResult();
		return entity;
	}

}
