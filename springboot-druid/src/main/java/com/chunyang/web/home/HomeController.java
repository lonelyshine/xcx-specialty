package com.chunyang.web.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chunyang.service.HomeService;
import com.chunyang.util.BaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 小程序中首页的相关展示内容
 * @author qinxuegang
 */
@Api(description ="小程序中首页的相关展示内容")
@Controller
@RequestMapping(value="/home")
public class HomeController extends BaseController{
	@Autowired
	private HomeService homeService;
	
	/**获取首页中banner的内容
	 * @return
	 */
	@ApiOperation("获取首页中banner的内容")
	@RequestMapping(value="/banner",method=RequestMethod.POST,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getBanner(){
		return responseArraySuccess(homeService.getHomeBanner());
	}
	
	/**
	 * 获取首页中展示的产品内容
	 * @return
	 */
	@ApiOperation("获取首页中展示的产品内容")
	public String getHomeProduct(){
		return responseArraySuccess(homeService.getHomeProduct());
	}
	
	
	
}
