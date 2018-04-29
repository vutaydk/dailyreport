package controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import common.util.Shiro;

@WebFilter("/api/*")
public class CheckLoginFilter implements Filter {
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;

		// check authentication
		boolean isLogin = "/login".equals(request.getPathInfo());
		// if (isLogin || Shiro.checkAuthentication())
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig fConfig) {
	}

	@Override
	public void destroy() {
	}
}
