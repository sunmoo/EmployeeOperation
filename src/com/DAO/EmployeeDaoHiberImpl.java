package com.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entities.Employee;
import com.util.HibernateUtil;

/**
 * This class mainly handle database operation using hibernate.
 * @Todo: need encapsulate new class handling two kinds of database operations.
 * @author miao shan 
 *
 */

public class EmployeeDaoHiberImpl implements EmployeeDao {

	/**
	 * create one new record.
	 * @param e
	 */
	public void create(Employee e) {
		
	}

	/**
	 * find an employee
	 * @param id
	 * @return
	 */
	public Employee find(int id) {
		Employee e = new Employee();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			String queryString = "from employee where id =: " + id;
			Query query = session.createQuery(queryString);
			query.setInteger("id", id);
			e = (Employee) query.uniqueResult();
			transaction.commit();
		} catch(HibernateException ex) {
			transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}		
		return e;
	}
	
	/**
	 * update one record, no need to find first;
	 * @param e
	 */
	public void update(Employee e) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(e);
			transaction.commit();
		} catch(HibernateException ex) {
			transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}				
	}
	
	@Override
	public Employee getEmployee(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> employeeList() {
		List<Employee> employeeList = new ArrayList<Employee>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Employee");
			employeeList = query.list();
		} catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}		
		return employeeList;
	}

	@Override
	public void addEmployee(Employee employee) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(employee);
			transaction.commit();
		} catch(HibernateException ex) {
			transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}

	}

	@Override
	public void updateEmployee(Employee employee) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteEmployee(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();			
			Employee e = (Employee) session.load(Employee.class, new Integer(id));
			session.delete(e);
			session.getTransaction().commit();
		} catch(HibernateException ex) {
			transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}	
	}	
}
