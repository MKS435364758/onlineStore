package com.onlineShopping.Web.security;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private static final Logger logger = LogManager.getLogger(SecurityConfig.class);

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET).permitAll()
                .antMatchers(HttpMethod.POST,SecurityContants.CUSTOMER_SIGN_UP).permitAll()
                .antMatchers(HttpMethod.POST).hasAnyRole("USER","ADMIN","SUPERADMIN")
                .antMatchers(HttpMethod.PUT).hasAnyRole("ADMIN","SUPERADMIN")
                .antMatchers(HttpMethod.DELETE).hasRole("SUPERADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

    @Bean
    public UserDetailsService users() {

        UserDetails admin = User.builder()
                .username("admin")
                .password(new BCryptPasswordEncoder().encode("admin-pass"))
                .roles("ADMIN")
                .build();
        UserDetails superAdmin = User.builder()
                .username("Admin@Super")
                .password(new BCryptPasswordEncoder().encode("SuperAdmin@Admin"))
                .roles("SUPERADMIN")
                .build();
        UserDetails userOne = User.builder()
                .username("userOne")
                .password(new BCryptPasswordEncoder().encode("user-pass"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin,userOne,superAdmin);
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            // Log successful authentication
            String username = authentication.getName();
            logger.info("Successful authentication for user: " + username);
//            response.sendRedirect("/home"); // Redirect to the home page after successful authentication
        };
    }


}
