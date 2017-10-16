package com.chunyang.filter;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam; 
/**
 * 这里添加filter监控
 * @author qinxuegang
 *
 */
@WebFilter(filterName="druidWebStatFilter",urlPatterns="/*",
initParams={  
        @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")// 忽略资源  
} )
public class druidWebStatFilter extends WebStatFilter{

}
