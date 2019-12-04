package es.uned.lsi.PL_ci.entity

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "alumnos")
class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "alumno_id_gen")
    @SequenceGenerator(name = "alumno_id_gen", sequenceName = "alumno_id_seq")
    @Column(name = 'alumno_id')
    Integer alumnoId

    @NotNull
    @Column(nullable = false)
    String nombre

    @NotNull
    @Column(name = 'apellido_1', nullable = false)
    String apellido1

    @Column(name = 'apellido_2', nullable = true)
    String apellido2

    String getApellido2() { apellido2 ?: '' }

    @NotNull
    @Column(nullable = false)
    String correo

    @NotNull
    @Column(nullable = false)
    String curso

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "alumno",
            cascade = CascadeType.MERGE,
            orphanRemoval = true)
    Set<CursoAlumno> cursosAlumno

    String repositorio

    @OneToOne(
            fetch = FetchType.LAZY,
            mappedBy = 'alumno',
            cascade = CascadeType.ALL
    )
    User user
}
