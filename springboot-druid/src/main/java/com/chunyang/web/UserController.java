package com.chunyang.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chunyang.service.UserService;
import com.chunyang.util.BaseController;

@RestController
public class UserController extends BaseController{
	@Autowired
	private UserService userService;
	@Autowired
	private StringRedisTemplate  stringRedisTemplate;
	@Autowired
	private RedisTemplate redisTemplate;
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public String getUser(@RequestParam(value="userName") String userName){
		//下面集成的redis不能生效的原因是因为本地没有安装redis（我配置的是连接本地的redis）
		/*//redis缓存保存字符串
		stringRedisTemplate.opsForValue().set("str", "字符串");
		System.out.println(stringRedisTemplate.opsForValue().get("str"));
		
		//保存对象
		redisTemplate.opsForValue().set("user1", userService.getUser(userName));
		redisTemplate.opsForValue().set("user2", userService.getUser(userName));
		redisTemplate.opsForValue().set("user3", userService.getUser(userName));
		redisTemplate.opsForValue().set("user4", userService.getUser(userName));
		
		System.out.println(redisTemplate.opsForValue().get("user1"));
		System.out.println(redisTemplate.opsForValue().get("user2"));
		System.out.println(redisTemplate.opsForValue().get("user3"));
		System.out.println(redisTemplate.opsForValue().get("user4"));*/
		return responseArraySuccess(userService.getUser(userName));
	}
}
