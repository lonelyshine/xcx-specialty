package com.chunyang.service;

import java.util.HashMap;
import java.util.List;

public interface UserService {
	public List<HashMap<String,Object>> getUser(String userName);
}
