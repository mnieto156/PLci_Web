package es.uned.lsi.PL_ci.entity

import javax.persistence.*

@Entity
@Table(name = 'curso_alumno')
class CursoAlumno {

    @EmbeddedId
    CursoAlumnoKey id

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId('alumnoId')
    Alumno alumno

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId('cursoId')
    Curso curso

    String repositorio

}
