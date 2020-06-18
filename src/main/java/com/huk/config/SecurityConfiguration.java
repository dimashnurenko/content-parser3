package com.huk.config;

import com.huk.services.UserService;
import com.huk.web.AuthenticationFilter;
import com.huk.web.AuthorizationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;
    private final String applicationSecretKey;

    public SecurityConfiguration(BCryptPasswordEncoder bCryptPasswordEncoder,
                                 UserService userService,
                                 @Value("${application.secret.key}") String applicationSecretKey) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
        this.applicationSecretKey = applicationSecretKey;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
            .and().csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/users").permitAll() //Разрешить все запросы
            .anyRequest().authenticated() // все кроме Пост должны быть аунтефицырованы
            .and()
            .addFilter(new AuthenticationFilter(authenticationManager(), applicationSecretKey))
            .addFilter(new AuthorizationFilter(authenticationManager(), applicationSecretKey))
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);//не создавать http сессии на запросы
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource corsConfiguration = new UrlBasedCorsConfigurationSource();
        corsConfiguration.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return corsConfiguration;
    }
}
