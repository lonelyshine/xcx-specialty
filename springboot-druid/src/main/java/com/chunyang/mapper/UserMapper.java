package com.chunyang.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
	//用户是否存在
		@Select("select t.user_name,t.psw from sampledb.t_user t where t.user_name =#{username}")
		public List<HashMap<String,Object>> getUserInfo(@Param(value="username") String username);
}
