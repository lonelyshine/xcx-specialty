package com.chunyang.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface HomeMapper {
	/**
	 * 查询home首页的banner内容
	 * @return 查询home首页的banner内容
	 */
	@Select("select t.imgUrl,t.productUrl from banner t")
	public List<HashMap<String, Object>> getHomeBanner();
	
	/**
	 * @return
	 */
	@Select("select ")
	public List<HashMap<String, Object>> getHomeProduct();
}
