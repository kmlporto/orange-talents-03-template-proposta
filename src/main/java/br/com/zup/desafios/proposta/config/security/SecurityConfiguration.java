package br.com.zup.desafios.proposta.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

import static br.com.zup.desafios.proposta.utils.Path.CARTAO_BIOMETRIA;
import static br.com.zup.desafios.proposta.utils.Path.PROPOSTA;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
            authorizeRequests
                .antMatchers(PROPOSTA + "/**").hasAuthority("SCOPE_primeiro-escopo")
                .antMatchers(CARTAO_BIOMETRIA).hasAuthority("SCOPE_primeiro-escopo")
                .antMatchers("/h2/**").permitAll()
                .anyRequest().authenticated())
            .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
            .headers().frameOptions().sameOrigin()
            .and().csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**");
    }
}
