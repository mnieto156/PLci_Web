package es.uned.lsi.PL_ci.service.impl

import es.uned.lsi.PL_ci.entity.Role
import es.uned.lsi.PL_ci.repository.RoleRepository
import es.uned.lsi.PL_ci.service.RoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import javax.persistence.EntityNotFoundException

@Service
class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository

    @Override
    Role findByIdOrError(String id) {
        roleRepository.findById(id).orElseThrow({new EntityNotFoundException()})
    }
}
