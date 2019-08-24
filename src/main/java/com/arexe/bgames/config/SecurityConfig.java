package com.arexe.bgames.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder passwordEncoder;
    private final DataSource dataSource;

    @Autowired
    SecurityConfig(BCryptPasswordEncoder passwordEncoder, DataSource dataSource) {
        this.passwordEncoder = passwordEncoder;
        this.dataSource = dataSource;
    }

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity httpSec) throws Exception {
        httpSec
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/index").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/adduser").permitAll()
                .antMatchers("/boardgame/**").permitAll()
                .antMatchers("/boardgame/search/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/boardgames").permitAll()
                .antMatchers(HttpMethod.GET, "/api/boardgames/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/boardgames/id/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/boardgames").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/boardgames/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/boardgames/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().csrf().disable()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/").usernameParameter("email")
                .passwordParameter("password")
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .and().exceptionHandling().accessDeniedPage("/denied");
    }

    @Override
    public void configure(WebSecurity webSec) throws Exception {
        webSec.ignoring()
                .antMatchers("/resources/**", "/statics/**", "/css/**", "/js/**", "/images/**", "/incl/**")
                .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/api/swagger-ui.html", "/webjars/**");

    }

}
