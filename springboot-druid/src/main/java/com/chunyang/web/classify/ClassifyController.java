package com.chunyang.web.classify;

import org.springframework.stereotype.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 小程序分类页面内容展示
 * @author qinxuegang
 *
 */
@Api(description="小程序分类页面内容展示")
@Controller
public class ClassifyController {
	
	/**
	 * @return
	 */
	@ApiOperation("获取小程序分类页面内容展示")
	public String getClassify(){
		return null;
	}
}
