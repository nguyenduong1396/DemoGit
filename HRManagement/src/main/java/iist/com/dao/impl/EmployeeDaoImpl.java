package iist.com.dao.impl;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import iist.com.dao.EmployeeDao;
import iist.com.entity.EmployeeEntity;

@Repository
@Transactional
public class EmployeeDaoImpl implements EmployeeDao{

    @Autowired
    private SessionFactory sessionFactory;
    
	@Override
	public EmployeeEntity findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "Select e from employee e where e.id = :id";
		Query<EmployeeEntity> query = session.createQuery(sql, EmployeeEntity.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	@Override
	public void addEmployee(EmployeeEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(entity);
		session.flush();
	}

	
}
