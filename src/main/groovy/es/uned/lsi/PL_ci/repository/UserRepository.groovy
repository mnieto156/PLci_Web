package es.uned.lsi.PL_ci.repository

import es.uned.lsi.PL_ci.entity.User
import org.springframework.data.repository.CrudRepository

interface UserRepository extends CrudRepository<User,String> {

}