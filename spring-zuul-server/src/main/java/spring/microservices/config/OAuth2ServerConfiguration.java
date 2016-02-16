//package spring.microservices.config;
//
//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.web.csrf.CsrfFilter;
//import org.springframework.security.web.csrf.CsrfToken;
//import org.springframework.security.web.csrf.CsrfTokenRepository;
//import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
//import org.springframework.security.web.header.HeaderWriterFilter;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.web.filter.RequestContextFilter;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * Created by wonwoo on 2016. 2. 14..
// */
//
//@Configuration
//@EnableOAuth2Sso
//public class OAuth2ServerConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.logout().and().antMatcher("/**").authorizeRequests()
//                .antMatchers("/login", "/user").permitAll()
////                .antMatchers(HttpMethod.GET, "/recommendations/**","/reviews/**","/people/**","/movie/**","/catalog/**","/likes/**").permitAll();
//                .anyRequest().authenticated().and().csrf()
//                .csrfTokenRepository(csrfTokenRepository()).and()
//                .addFilterBefore(new RequestContextFilter(), HeaderWriterFilter.class)
//                .addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);
//    }
//    private Filter csrfHeaderFilter() {
//
//        return new OncePerRequestFilter() {
//            @Override
//            protected void doFilterInternal(HttpServletRequest request,
//                                            HttpServletResponse response, FilterChain filterChain)
//                    throws ServletException, IOException {
//                CsrfToken csrf = (CsrfToken) request
//                        .getAttribute(CsrfToken.class.getName());
//                if (csrf != null) {
//                    Cookie cookie = new Cookie("XSRF-TOKEN",
//                            csrf.getToken());
//                    cookie.setPath("/");
//                    response.addCookie(cookie);
//                }
//                filterChain.doFilter(request, response);
//            }
//        };
//    }
//
//    private CsrfTokenRepository csrfTokenRepository() {
//        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
//        repository.setHeaderName("X-XSRF-TOKEN");
//        return repository;
//    }
//
//
//
//}
