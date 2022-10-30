package com.gl.StdntMgnt.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gl.StdntMgnt.model.Student;

@Repository
public class StudentDaoImpl implements StudentDao {
	@Autowired
	SessionFactory factory;

	@Override
	@Transactional
	public void saveStudent(Student student) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(student);
	}

	@Override
	@Transactional
	public Student findById(int id) {
		Session session = factory.getCurrentSession();
		Student st = session.get(Student.class, id);
		System.out.println(st);
		return st;
	}

	@Override
	@Transactional
	public List<Student> findAll() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("from Student");
		return query.getResultList();
	}

	@Override
	@Transactional
	public void deleteStudent(int id) {
		Session session = factory.getCurrentSession();
		Student st = session.load(Student.class, id);
		session.delete(st);

	}

	@Override
	@Transactional
	public List<Student> search(String name, String department) {
		Session session = factory.getCurrentSession();
		Criteria c = session.createCriteria(Student.class);
		Criterion n = Restrictions.eq("name", name);
		Criterion d = Restrictions.eq("department", department);
		Criterion combine = Restrictions.and(n, d);
		c.add(combine);
		return c.list();
		// TODO Auto-generated method stub
	}

}
