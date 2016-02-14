package spring.microservices.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

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

            http.requestMatchers()
                    .antMatchers("/test")
                    .and()
                    .authorizeRequests()
                    .antMatchers("/test")
                    .access("#oauth2.hasScope('read')");

        }
    }

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        private TokenStore tokenStore = new InMemoryTokenStore();

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private UserDetailsService userDetailsService;

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints.tokenStore(this.tokenStore).authenticationManager(this.authenticationManager)
                    .userDetailsService(userDetailsService);
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.inMemory().withClient("wonwooapp").authorizedGrantTypes("password", "refresh_token")
                    .authorities("USER").scopes("read", "write").resourceIds(RESOURCE_ID).secret("XX0000001");
            // .and()
            // .withClient("myapp2")
            // .authorizedGrantTypes("authorization_code")
            // .authorities("ROLE_CLIENT")
            // .scopes("read", "write")
            // .resourceIds(RESOURCE_ID)
            // .secret("XX002");
        }

        @Bean
        @Primary
        public DefaultTokenServices tokenServices() {
            DefaultTokenServices tokenServices = new DefaultTokenServices();
            tokenServices.setSupportRefreshToken(true);
            tokenServices.setTokenStore(this.tokenStore);
            return tokenServices;
        }
    }

}
