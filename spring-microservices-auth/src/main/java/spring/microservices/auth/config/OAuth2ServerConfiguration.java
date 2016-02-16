package spring.microservices.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

import javax.sql.DataSource;

/**
 * Created by wonwoo on 2016. 2. 14..
 */

@Configuration
public class OAuth2ServerConfiguration {
    private static final String RESOURCE_ID = "api";

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId(RESOURCE_ID);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {

            http
                    // Just for laughs, apply OAuth protection to only 2 resources
                    .requestMatcher(new OrRequestMatcher(
                            new AntPathRequestMatcher("/me")
                    ))
                    .authorizeRequests()
                    .anyRequest().access("#oauth2.hasScope('read')");
//

//            http
//                    .re
//                    .antMatchers("/login").permitAll()
//                    .authorizeRequests()
//                    .anyRequest().access("#oauth2.hasScope('read')");

//            http.authorizeRequests()
//                    .antMatchers("/login")
//                    .permitAll()
//                    .and()
//                    .requestMatchers()
//                    .antMatchers("/me")
//                    .and()
//                    .authorizeRequests()
//                    .antMatchers("/me")
//                    .access("#oauth2.hasScope('read')");

//            http.logout().and().antMatcher("/**").authorizeRequests()
//                    .antMatchers("/login").permitAll();

//            http.logout().and().antMatcher("/**").authorizeRequests()
//                    .antMatchers("/index.html", "/home.html", "/", "/login", "/beans","/user").permitAll()
//                    .antMatchers(HttpMethod.GET, "/recommendations/**","/reviews/**","/people/**","/movie/**","/catalog/**","/likes/**").permitAll()
//                    .anyRequest().authenticated().and().csrf()

        }

    }

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {


        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private UserDetailsService userDetailsService;


        @Bean
        JdbcClientDetailsService jdbcClientDetailsService(){
            return new JdbcClientDetailsService(dataSource);
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//            endpoints.authenticationManager(authenticationManager);
            endpoints.authorizationCodeServices(authorizationCodeServices()).tokenStore(this.tokenStore()).authenticationManager(this.authenticationManager)
                    .userDetailsService(userDetailsService);
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

//            clients.inMemory()
//                    .withClient("acme")
//                    .secret("acmesecret")
//                    .authorizedGrantTypes("authorization_code", "refresh_token",
//                            "password").scopes("openid");
            clients.withClientDetails(jdbcClientDetailsService());

//            clients.inMemory().withClient("wonwooapp").authorizedGrantTypes("password", "refresh_token")
//                    .authorities("USER").scopes("read", "write").resourceIds(RESOURCE_ID).secret("XX0000001");
//            clients.inMemory()
//             .withClient("myapp2")
//             .authorizedGrantTypes("authorization_code")
//             .authorities("ROLE_CLIENT")
//             .scopes("read", "write")
//             .resourceIds(RESOURCE_ID)
//             .secret("XX002");
        }


        @Bean
        protected AuthorizationCodeServices authorizationCodeServices() {
            return new JdbcAuthorizationCodeServices(dataSource);
        }


        @Autowired
        private DataSource dataSource;

        @Bean
        public JdbcTokenStore tokenStore() {
            return new JdbcTokenStore(dataSource);
        }
    }

}
