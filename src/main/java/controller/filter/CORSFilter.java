package controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class CORSFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse http = (HttpServletResponse) response;
		http.addHeader("Access-Control-Allow-Origin", "*");
		http.addHeader("Access-Control-Allow-Credentials", "true");
		http.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig fConfig) {
	}

	@Override
	public void destroy() {
	}
}
