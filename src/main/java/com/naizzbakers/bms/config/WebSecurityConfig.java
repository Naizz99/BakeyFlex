package com.naizzbakers.bms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private BmsAuthenticationSuccessHandler successHandler;
	
	@Autowired
	private BMSLogoutHandler logoutHandler;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/index", "/login","/menu", "/contact", "/deals", "/blog", "/gallery", "/about-us", "/order",
						"/login","/saveInventory").permitAll()
				.antMatchers("/listUsers").hasAnyAuthority("admin")
				.anyRequest().authenticated()
				.and()
				.formLogin()
					.loginPage("/login").permitAll()
					.successHandler(successHandler)
					.usernameParameter("email")
					.passwordParameter("password")
				.and()
				.logout().permitAll()
		        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		        .addLogoutHandler(logoutHandler)
		        .invalidateHttpSession(true)
		        .clearAuthentication(true)
		        .deleteCookies("JSESSIONID")		                
		        .logoutSuccessUrl("/login?logout")
		        .and().exceptionHandling().accessDeniedPage("/access_denied");
				;
	}
	
//	.logout().logoutSuccessUrl("/login?logout").permitAll()
//	.and()
//	.exceptionHandling().accessDeniedPage("/403")
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/admin/**", "/admin/css/**", "/admin/js/**", "/customer/**", "/resources/**", "/static/**",
                		"/static/customer/**", "/assets/**", "/customer/assets/**", "/css/**","/fonts/**", "/images/**", 
                		"/js/**", "assets/**", "assets/font-awesome-4.7.0/**", "assets/font-awesome-4.7.0/css/**", "/image/**", "/secure/**");
    }
	
}
