package es.uned.lsi.PL_ci.service

import es.uned.lsi.PL_ci.entity.Role

interface RoleService {

    Role findByIdOrError(String id)

}