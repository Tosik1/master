package main.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/post/moderation", "/api/moderation").hasAuthority("user:moderate")
                .antMatchers(HttpMethod.PUT, "/api/settings").hasAuthority("user:moderate")
                .antMatchers("/api/post/my", "/api/profile/my", "/api/statistics/my", "/api/post/like", "/api/post/dislike", "/api/auth/logout").hasAuthority("user:write")
                .antMatchers(HttpMethod.POST, "/api/post", "/api/image", "/api/comment").hasAuthority("user:write")
                .antMatchers(HttpMethod.PUT, "/api/post/{ID}").hasAuthority("user:write")
//                    .antMatchers("").hasRole("USER")
                .antMatchers("/**").permitAll() // выдаем доступы на разделы сайта всем(юзерам, модераторам)
                .anyRequest() // все реквесты мапируем
                .authenticated()// и они будут использоваться для авторизации
                .and() //следующий блок настроек
                .formLogin().disable() // страница логина spring security октлючена
                .httpBasic().disable()
                .logout().logoutSuccessUrl("/");
//        .antMatchers("/api/post/moderation", "/api/moderation", "/api/settings").hasRole("MODERATOR")
//                    .antMatchers("/api/post/**", "/api/image", "/api/comment", "/api/profile/my", "/api/statistics/my", "").permitAll()
    }

    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
