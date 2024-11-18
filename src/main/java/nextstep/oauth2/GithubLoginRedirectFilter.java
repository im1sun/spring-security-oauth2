package nextstep.oauth2;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

public class GithubLoginRedirectFilter extends GenericFilterBean {

    public static final String AUTHORIZATION_REQUEST_URI = "/oauth2/authorization/github";
    public static final String GITHUB_AUTHORIZATION_URI = "https://github.com/login/oauth/authorize?";
    public static final String REDIRECT_URI = "http://localhost:8080/login/oauth2/code/github";


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("GithubLoginRedirectFilter.doFilter");

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        if (!httpRequest.getRequestURI().equals(AUTHORIZATION_REQUEST_URI)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        httpResponse.sendRedirect("https://github.com/login/oauth/authorize?response_type=code&client_id=Ov23liKqs4fJ24nNmjD7&scope=read:user&redirect_uri=http://localhost:8080/login/oauth2/code/github");

//        String paramsQuery = UriComponentsBuilder.newInstance()
//                .queryParam("client_id", "Ov23liKqs4fJ24nNmjD7")
//                .queryParam("response_type", "code")
//                .queryParam("scope", "read:user")
//                .queryParam("redirect_uri", REDIRECT_URI)
//                .build()
//                .toUri()
//                .getQuery();
//        httpResponse.sendRedirect(GITHUB_AUTHORIZATION_URI + paramsQuery);

    }
}
