package ro.msg.learning.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@SpringBootApplication
public class ShopApplication {
	@Bean
	public WebSecurityConfigurerAdapter webSecurityConfig(DataSource dataSource) {
		return new WebSecurityConfigurerAdapter() {
			@Override
			protected void configure(HttpSecurity http) throws Exception {
				http.authorizeRequests()
						.antMatchers("/").permitAll() // you can allow the root endpoint ( also will be containing the default /h2-console endpoint ) for all users
						// or put some role restriction on the specific "/h2-console" endpoint to the admin user you are going to be logging in with.
						.antMatchers("/admin/**").hasRole("ADMIN")
						.and()
						.csrf().disable() //rest of the configs below according to your needs.
						.headers().frameOptions().disable()
						.and()
						.formLogin();
			}

			@Override
			protected void configure(AuthenticationManagerBuilder builder) throws Exception {
				builder.jdbcAuthentication()
						.dataSource(dataSource);
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}

}
