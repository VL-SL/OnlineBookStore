package sbertech.svm.onlinebookstore.configurations;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import sbertech.svm.onlinebookstore.services.CustomUserDetailsService;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
    private final CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/", "/book/**","/registration", "/css/**", "/js/**", "/images/**", "/h2-console/**").permitAll()
                                .requestMatchers("/cart/**").hasRole("USER")
                                .requestMatchers("/employee/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                                .permitAll()
                                .defaultSuccessUrl("/", true)
                                .failureUrl("/login?error=true")

                )
                .logout(logout -> logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                );

        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }
}
