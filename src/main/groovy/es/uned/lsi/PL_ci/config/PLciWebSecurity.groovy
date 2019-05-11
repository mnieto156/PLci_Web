package es.uned.lsi.PL_ci.config

import es.uned.lsi.PL_ci.entity.Role
import es.uned.lsi.PL_ci.entity.User
import es.uned.lsi.PL_ci.repository.RoleRepository
import es.uned.lsi.PL_ci.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


import javax.transaction.Transactional

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
        http    .csrf().disable()
        http    .authorizeRequests()
                    .antMatchers("/ayuda","/webjars/**").permitAll()
                    .anyRequest().authenticated()

        http    .formLogin()
                    .loginPage('/login')
                .failureUrl('/login?error')
                    .permitAll()
                .and()
                .logout().logoutUrl('/logout').permitAll()
                .and()
                .exceptionHandling().accessDeniedPage('/login?denied')

    }

    @Bean
    AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager()
    }

    @Autowired
    void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())
    }

    @Autowired
    @Transactional
    void initUsers(
            RoleRepository roleRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        def roleAdmin = roleRepository.findById("ROLE_ADMIN").orElse(null)
        if (roleAdmin == null) {
            roleAdmin = new Role(
                    authority: 'ROLE_ADMIN',
                    description: 'Administrador de pagina'
            )
            roleRepository.save(roleAdmin)
        }

        def roleAlumno = roleRepository.findById("ROLE_ALUMNO").orElse(null)
        if (roleAlumno == null) {
            roleAlumno = new Role(
                    authority: 'ROLE_ALUMNO',
                    description: 'Alumno de la asignatura'
            )
            roleRepository.save(roleAlumno)
        }

        def userAdmin = userRepository.findById('admin').orElse(null)
        if (userAdmin == null) {
            userAdmin = new User(
                    username: 'admin',
                    password: passwordEncoder.encode('1234'),
                    authorities: [roleAdmin])
            userRepository.save(userAdmin)
        }

        def userAlumno = userRepository.findById('mnieto156').orElse(null)
        if (userAlumno == null) {
            userAlumno = new User(
                    username: 'mnieto156',
                    password: passwordEncoder.encode('deunan'),
                    authorities: [roleAlumno])
            userRepository.save(userAlumno)

        }
    }
}
