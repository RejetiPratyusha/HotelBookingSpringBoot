package com.springboot.HotelBookingSystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springboot.HotelBookingSystem.service.UserService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserService userService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("config called");
		auth.authenticationProvider(getProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/hr/add").permitAll().antMatchers("/feelhome/customer/add").permitAll()
//				.antMatchers("/hotel/getAllByLocationName/{name}").permitAll().antMatchers("/customer/add").permitAll()
//				.antMatchers("/feelhome/rooms/getByHotel/{hid}").permitAll().antMatchers("/user/login").permitAll()
//				.antMatchers("/feelhome/book/{cid}/{rid}").permitAll().antMatchers("/feedback/{cid}/{hid}").permitAll()
//				.antMatchers("/feedbackForHotel/{hid}").permitAll().antMatchers("/feelhome/getalllocations").permitAll()
//				.antMatchers("/feelhome/getPrice").permitAll()
//				.antMatchers("/hotel/getByHotel/{hid}", "/feelhome/room/add/{hid}", "/hotel/getbyAdmin/{aid}",
//						"/feelhome/bookingsByHotelId/{hid}", "/feelhome/booking/{cid}",
//						"/feelhome/customer/getone/{cid}")
//				.permitAll()
//
//				.anyRequest().authenticated().and().httpBasic().and().csrf().disable().cors().disable();
		http.authorizeRequests()
		.antMatchers(HttpMethod.OPTIONS).permitAll()
		 .antMatchers(HttpMethod.GET,"/user/login").authenticated()
		 .antMatchers("/hotel/getAllByExecutive/{eid}","/feelhome/location/add/{eid}","/hoteladmin/add/{eid}","/hotel/add/{aid}/{lid}").hasAnyAuthority("EXECUTIVE")
		 .antMatchers("/executive/add").hasAnyAuthority("HR")
		 .antMatchers("/feelhome/room/add/{hid}","/hoteladmin/getadmin/{id}","/feelhome/room/update/{rid}","/feelhome/bookingsByHotelId/{hid}").hasAnyAuthority("HOTEL_ADMIN")
		 .antMatchers("/feelhome/rooms/getByHotel/{hid}").hasAnyAuthority("HOTEL_ADMIN","CUSTOMER")
		 .anyRequest().permitAll()
		 .and().httpBasic()
		 .and().cors().disable()
		 .csrf().disable();

	}

	@Bean
	public PasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}

	public AuthenticationProvider getProvider() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setPasswordEncoder(getEncoder());
		dao.setUserDetailsService(userService);
		return dao;
	}
	
	@Bean
	public Logger getLogger() {
		return LoggerFactory.getLogger("Log records");
	}
}
