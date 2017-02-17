package models;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/", "/home", "/books", "/books/**").permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().logout().permitAll();
    // for the REST API
    http.csrf().disable().authorizeRequests().and().httpBasic().realmName("REALM1").authenticationEntryPoint(getBasicAuthEntryPoint());
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    Repos.auth = auth;
    auth.inMemoryAuthentication().withUser("test").password("test").roles("USER");
    auth.inMemoryAuthentication().withUser("asdf").password("asdf").roles("ADMIN");
  }

  @Bean
  public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
    return new CustomBasicAuthenticationEntryPoint();
  }

  /* To allow Pre-flight [OPTIONS] request from browser */
  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
  }
}
