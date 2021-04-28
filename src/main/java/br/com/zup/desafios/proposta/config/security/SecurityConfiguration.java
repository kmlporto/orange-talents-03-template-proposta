package br.com.zup.desafios.proposta.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;


@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
            authorizeRequests
//                .antMatchers(API + "/**").hasAuthority("SCOPE_primeiro-escopo")
                .antMatchers("/h2/**").permitAll()
                .anyRequest().permitAll())
            .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
            .headers().frameOptions().sameOrigin()
            .and().csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**");
    }
}
