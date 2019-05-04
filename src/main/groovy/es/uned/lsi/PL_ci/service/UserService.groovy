package es.uned.lsi.PL_ci.service

import es.uned.lsi.PL_ci.entity.User

interface UserService {
    List<User> findAll()

    User findById(String id)

    User findByIdOrError(String id)

    User create(User user)

    User updatePassword(String id, String password)

    User updateEnabled(String id, boolean enabled)

    User deleteById(String id)
}