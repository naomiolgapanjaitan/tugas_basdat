package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

@Override
protected void configure(HttpSecurity http) throws Exception {
	http
	.csrf().disable()
	.authorizeRequests()
	.antMatchers("/register/mahasiswa").permitAll()
	.antMatchers("/register/donatur").permitAll()
	.antMatchers("/register/mahasiswa/submit").permitAll()
	.antMatchers("/register/individuDonatur/submit").permitAll()
	
	.anyRequest().authenticated()
	.and()
	.formLogin()		
	.loginPage("/login").permitAll()
	.defaultSuccessUrl("/login",true)
	.and()
	.logout().permitAll();
}

@Override
public void configure(WebSecurity web) throws Exception {
    web
       .ignoring()
       .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/upload-dir")
       .antMatchers("/dist/css/adminlte.min.css")
       .antMatchers("/plugins/jquery/jquery.min.js")
       .antMatchers("/plugins/bootstrap/js/bootstrap.bundle.min.js")
       .antMatchers("/plugins/iCheck/icheck.min.js");
    	
}

@Autowired
DataSource dataSource;

@Autowired
public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception
{
	auth.jdbcAuthentication().dataSource(dataSource)
	.passwordEncoder(NoOpPasswordEncoder.getInstance())
	.usersByUsernameQuery("SELECT U.username, U.password, '1' as enabled FROM pengguna U WHERE U.username =?")
	.authoritiesByUsernameQuery("select username, role from pengguna where username=?");
}
}