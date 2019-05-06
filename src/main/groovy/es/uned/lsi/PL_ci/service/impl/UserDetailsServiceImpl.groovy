package es.uned.lsi.PL_ci.service.impl

import es.uned.lsi.PL_ci.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

import javax.transaction.Transactional

@Service
@Transactional
class UserDetailsServiceImpl implements  UserDetailsService{
    @Autowired
    UserService userService

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        def user = userService.findById(username)
        if (user==null) throw new UsernameNotFoundException(username)
        return  user
    }
}
