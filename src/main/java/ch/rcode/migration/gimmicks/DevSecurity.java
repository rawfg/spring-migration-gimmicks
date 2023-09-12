package ch.rcode.migration.gimmicks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsUtils;

@Configuration
public class DevSecurity {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    @Order(0)
    public SecurityFilterChain innerChain(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/**")
                .authenticated()
                .and().httpBasic().disable()
                .cors()
                .and().csrf().disable()
                .formLogin()
                .loginPage("/login")
                .permitAll();
        return http.build();
    }

    @Bean
    @Order(10)
    public SecurityFilterChain secondChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest)
                .permitAll()
                .and().anonymous().and()
                .httpBasic().disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
