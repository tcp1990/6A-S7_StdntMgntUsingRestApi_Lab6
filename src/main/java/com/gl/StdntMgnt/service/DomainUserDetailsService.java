package com.gl.StdntMgnt.service;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gl.StdntMgnt.model.DomainUserDetails;
import com.gl.StdntMgnt.model.Student;
import com.gl.StdntMgnt.model.User;
import com.gl.StdntMgnt.repository.UserRepository;

@Service
public class DomainUserDetailsService implements UserDetailsService {

	@Autowired
	SessionFactory factory;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Session session = factory.getCurrentSession();
		Criteria c = session.createCriteria(User.class);
		Criterion n = Restrictions.eq("username", username);
		c.add(n);
		var userList = c.list();
		if (!userList.isEmpty()) {
			User user = (User) userList.get(0);
			System.out.println("User from the repository ");
			System.out.println(user);
			// convert into UserDetails object which Spring Security can understand
			return new DomainUserDetails(user);
		} else {
			throw new UsernameNotFoundException("Invalid User");
		}

	}
}