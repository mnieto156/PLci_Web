package es.uned.lsi.PL_ci.service.impl

import es.uned.lsi.PL_ci.entity.User
import es.uned.lsi.PL_ci.repository.UserRepository
import es.uned.lsi.PL_ci.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

import javax.persistence.EntityNotFoundException

@Service
class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository

    @Autowired
    PasswordEncoder passwordEncoder

    @Override
    List<User> findAll() {
        userRepository.findAll().asList()
    }

    @Override
    User findById(String id) {
        userRepository.findById(id).orElse(null)
    }

    @Override
    User findByIdOrError(String id) {
        userRepository.findById(id).orElseThrow({new EntityNotFoundException()})
    }

    @Override
    User create(User user) {
        user.password=passwordEncoder.encode(user.password)
        userRepository.save(user)
    }

    @Override
    User updatePassword(String id, String password) {
        def user = findByIdOrError(id)
        user.password=passwordEncoder.encode(password)
        userRepository.save(user)
    }

    @Override
    User updateEnabled(String id, boolean enabled) {
        def user = findByIdOrError(id)
        user.enabled = enabled
        userRepository.save(user)
    }

    @Override
    User deleteById(String id) {
        def user = findByIdOrError(id)
        userRepository.delete(user)
        user
    }
}
