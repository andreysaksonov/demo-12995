package com.example.demo12995;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jersey.ResourceConfigCustomizer;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import javax.ws.rs.Path;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class Demo12995Application {

	@Bean
	public ResourceConfig jerseyResourceConfig() {
		return new ResourceConfig();
	}

	@Bean
	public ResourceConfigCustomizer jerseyResourceConfigCustomizer(ListableBeanFactory beanFactory) {
		return config -> beanFactory.getBeansWithAnnotation(Path.class)
				.forEach((beanName, resource) -> config.register(resource.getClass()));
	}

	@Bean
	public WebSecurityConfigurerAdapter webSecurityConfigurer() {
		return new WebSecurityConfigurerAdapter() {
			@Override
			protected void configure(HttpSecurity http) throws Exception {
				http
						.authorizeRequests()
						.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
						//.antMatchers("/**").authenticated()
						.and()
						.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
						.and()
						.httpBasic();
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(Demo12995Application.class, args);
	}
}
