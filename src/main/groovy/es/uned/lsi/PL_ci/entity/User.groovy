package es.uned.lsi.PL_ci.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.userdetails.UserDetails

import javax.persistence.*

@Entity
@Table(name='users')
class User implements  UserDetails{
    @Id
    String username

    String password

    boolean enabled=true
    boolean accountNonExpired = true

    boolean accountNonLocked = true

    boolean credentialsNonExpired = true

    @OneToOne(orphanRemoval = true)
    @JsonIgnore
    Alumno alumno

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = 'user_role',
            joinColumns = @JoinColumn(name = 'username'),
            inverseJoinColumns = @JoinColumn(name = 'authority'))
    Set<Role> authorities

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    Set<Comentario> comentarios

}
