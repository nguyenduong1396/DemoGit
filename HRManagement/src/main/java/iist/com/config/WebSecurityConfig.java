package iist.com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import iist.com.jwt.JWTAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				// No need authentication.
		        .antMatchers("/").permitAll()
				.antMatchers("/auth/login").permitAll() 
//				.antMatchers("/test/**").permitAll()
				// Need authentication.
				.anyRequest().authenticated()
				//
				.and().exceptionHandling().accessDeniedPage("/403")
				//
				.and()
				//
				// Add Filter 1 - JWTLoginFilter
				//
//				.addFilterBefore(new JWTLoginFilter("/auth/login", authenticationManager()),
//						UsernamePasswordAuthenticationFilter.class)
				// Add Filter 2 - JWTAuthenticationFilter
				//
				.addFilterBefore(new JWTAuthenticationFilter(authenticationManager()),
						UsernamePasswordAuthenticationFilter.class)
				.csrf().disable();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**")
		.antMatchers("/app/**/*.{js,html}")
		.antMatchers("/content/**")
		.antMatchers("/v2/api-docs/**")
		.antMatchers("/swagger.js")
		.antMatchers("/swagger-ui.html")
		.antMatchers("/configuration/ui")
		.antMatchers("/swagger-resources/**")
		.antMatchers("/webjars/**");
	}

}