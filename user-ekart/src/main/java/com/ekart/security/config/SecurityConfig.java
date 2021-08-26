package com.ekart.security.config;

import com.ekart.security.provider.MyAuthProvider;
import com.ekart.security.service.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //@Autowired
    //private AuthenticationProvider myAuthProvider;

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetails();
    }

    @Bean
    public PasswordEncoder pe() {
        return new BCryptPasswordEncoder();
    }
	/*public PasswordEncoder pe() {
		return NoOpPasswordEncoder.getInstance();
	}*/

    @Bean
    public AuthenticationProvider myAuthProvider(){
        return new MyAuthProvider();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws
            Exception {
        auth.authenticationProvider(myAuthProvider());
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(pe());
        return provider;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/profile").authenticated() // means these
                // all are
                // accessible
                // (public)
                .anyRequest().permitAll()
                .and()

                .formLogin()
                .loginPage("/user/login-form")
                .usernameParameter("email")
                .permitAll() // after login (secure pages are accessible)
                .and()
                //.logout().logoutSuccessUrl("/").permitAll();
                .logout().invalidateHttpSession(true).clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/user/login-form")
                .permitAll();
    }

}

