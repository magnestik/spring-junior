package ru.iteco.teachbase.springjunior.gateway.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.iteco.teachbase.springjunior.gateway.api.LogoutHandler;

@Configuration
public class SecurityConfig  {

    private final LogoutHandler logoutHandler;

    public SecurityConfig(LogoutHandler logoutHandler) {
        this.logoutHandler = logoutHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests().mvcMatchers("/").permitAll()
            .anyRequest().authenticated()
            .and().oauth2Login()
            .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).addLogoutHandler(logoutHandler);
        return http.build();
    }

}
