package es.uned.lsi.PL_ci.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.Table
import javax.persistence.GenerationType
import javax.validation.constraints.NotNull
import javax.persistence.Column
import javax.persistence.ManyToOne
import javax.persistence.JoinColumn


@Entity
@Table(name="usuarios")
class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name='usuario_id')
    Integer usuarioId

    @NotNull
    @Column(nullable = false)
    String nombre

    @NotNull
    @Column(name='apellido_1',nullable = false)
    String apellido1

    @Column(name='apellido_2',nullable = true)
    String apellido2
    String getApellido2(){apellido2 ?: ''}

    @NotNull
    @Column(nullable = false)
    String correo

    @ManyToOne
    @JoinColumn(name = "perfil_id", referencedColumnName = "perfil_id", nullable = false)
    Perfil perfil

}
