package es.uned.lsi.PL_ci.entity

import org.springframework.security.core.GrantedAuthority

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Role implements GrantedAuthority {
    @Id
    String authority

    String description
}
