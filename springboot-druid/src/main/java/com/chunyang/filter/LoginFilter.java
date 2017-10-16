package com.chunyang.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 使用注解标注过滤器
 * @WebFilter将一个实现了javax.servlet.Filter接口的类定义为过滤器
 * 属性filterName声明过滤器的名称,可选
 * 属性urlPatterns指定要过滤 的URL模式,也可使用属性value来声明.(指定要过滤的URL模式是必选属性)
 * 
 * @author   qinxuegang
 */
@WebFilter(filterName="LoginFilter",urlPatterns="/*")
public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
				HttpServletRequest req = (HttpServletRequest) request;
				HttpServletResponse res = (HttpServletResponse) response;
				//从SESSION获取对象
				Object obj = req.getSession().getAttribute("user");
				String servletPath = req.getServletPath();
				//System.out.println(obj+"0");
				if(obj != null){
					//System.out.println(obj+"3");
					chain.doFilter(request, response);//请求：我自己定义请求，例外就是页面JS,JSP,HTML
				}else{
					//获取访问的地址
					/**
					 * res.getScheme() 获取访问的协议 http https
					 * res.getServerName() 获取访问的项目名称
					 * res.getServerPort() 获取访问的端口
					 * res.getContextPath();//获取项目名称
					 * res.getServletPath();//实际访问的路径
					 */
					String path = req.getContextPath();
					String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path+"/";
					
					if(servletPath.contains(".")){
						int index = servletPath.lastIndexOf(".");
						String suffix = servletPath.substring(index);
						//System.out.println("suffix:"+suffix);
						if(".js.html.css.jpg.png".contains(suffix)||"/login.html".equals(servletPath)){
							chain.doFilter(request, response);
						}else{//重定向
							System.out.println("重定向");
							res.sendRedirect(basePath);
						}
					}else{//这里是处理我们自己定义请求
						String loginStatus = (String) req.getSession().getAttribute("loginStatus");
						if(loginStatus!=null&&!loginStatus.equals("")&&loginStatus.equals("loginout")){//如果是登出后 刷新原有的页面则需要进行重定向到登录页面
							req.getSession().invalidate();//清除session
							res.sendRedirect(basePath);
						}else{
						//System.out.println(servletPath+"1");
						if(servletPath.contains("login")||servletPath.contains("regist")){
							//System.out.println(req.getServletPath()+"::::::::"+res.getLocale());
							chain.doFilter(request, response);
						}else if(servletPath.contains("ficationCode")||servletPath.contains("regist")){
							chain.doFilter(request, response);
						}else if(servletPath.contains("repassword")||servletPath.contains("regist")){
							chain.doFilter(request, response);
						}else{//重定向
							System.out.println(basePath+"login.html");
							res.sendRedirect(basePath+"login.html");
						}
					    }
					}
				}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}
