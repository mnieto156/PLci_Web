package es.uned.lsi.PL_ci.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class PLciWebSecurity extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder()
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .formLogin()
                    .loginPage('/login')
                    .failureUrl('/login/authfail')
                    .permitAll()
                .and()
                .logout().logoutUrl('/logout').permitAll()
                .and()
                .exceptionHandling().accessDeniedPage('/login/denied')
                .and()
                .authorizeRequests()
                    .anyRequest().fullyAuthenticated()
    }

    @Bean
    AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager()
    }

    @Autowired
    void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())
    }
}
