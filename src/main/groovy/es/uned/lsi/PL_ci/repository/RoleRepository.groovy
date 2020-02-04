package es.uned.lsi.PL_ci.repository

import es.uned.lsi.PL_ci.entity.Role
import org.springframework.data.repository.CrudRepository

interface RoleRepository extends CrudRepository<Role,String> {

}