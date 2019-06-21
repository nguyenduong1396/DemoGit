package iist.com.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import iist.com.dao.UserRoleDao;

@Repository
@Transactional
public class UserRoleDaoImpl implements UserRoleDao {

    @Autowired
    private SessionFactory sessionFactory;
    
	@Override
	public List<String> getUserRoleByUserId(String userId) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "Select r.roleName AS roleName from Role r left join UserRoleEntity u on r.roleId = u.roleId where u.userId = :userId";
		Query<String> query = session.createQuery(sql, String.class);
		query.setParameter("userId", userId);
		return query.getResultList();
	}

}
