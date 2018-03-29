package com.wha.springmvc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wha.springmvc.dao.UserDao;
import com.wha.springmvc.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao dao;

	public List<User> findAllUsers() {
	
		return dao.findAllUsers();
	}
	
	public User findById(long id) {

		return dao.findById((int)id);
	}
	
	public User findByName(String name) {

		return dao.findByName((String) name);
	}
	
	public void saveUser(User user) {
	
		dao.save(user);
	}

	public void updateUser(User user) {
	
		
		User entity = dao.findById((int)user.getId());
		if(entity!=null){
			entity.setUsername(user.getUsername());
			entity.setAddress(user.getAddress());
			entity.setEmail(user.getEmail());
			
		}
		dao.save(entity);
	}

	public void deleteUserById(long id) {

		dao.deleteUserById((int) id);
		
	}

	public boolean isUserExist(User user) {
		return dao.findByName((String) user.getUsername())!=null;
	}
	
	public void deleteAllUsers(){
		dao.deleteAllUsers();
	}

	

}
