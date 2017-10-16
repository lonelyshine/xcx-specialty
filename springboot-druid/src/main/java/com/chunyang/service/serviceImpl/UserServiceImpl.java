package com.chunyang.service.serviceImpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chunyang.mapper.UserMapper;
import com.chunyang.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<HashMap<String, Object>> getUser(String userName) {
		return userMapper.getUserInfo(userName);
	}

}
