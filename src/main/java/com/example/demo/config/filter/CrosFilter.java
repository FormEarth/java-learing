package com.example.demo.config.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

/**
 * 处理跨域请求服务端Filter
 * @author Jason
 *
 */

@Component
public class CrosFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        //如果要做细的限制，仅限某域名下的可以进行跨域访问到此，可以将*改为对应的域名。
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "*");
        res.setHeader("Access-Control-Max-Age", "1728000");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Allow-Headers", "*");
        res.addHeader("Access-Control-Allow-Headers", "*");
        chain.doFilter(request, response);

	}

}
