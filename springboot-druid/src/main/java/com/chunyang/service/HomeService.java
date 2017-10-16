package com.chunyang.service;

import java.util.HashMap;
import java.util.List;

/**
 * 小程序首页服务接口
 * @author qinxuegang
 *
 */
public interface HomeService {
	
	/**
	 * 获取首页面的banner内容
	 * @return 首页banner内容
	 */
	public List<HashMap<String,Object>> getHomeBanner();
	/**
	 * @return
	 */
	public List<HashMap<String,Object>> getHomeProduct();
}
