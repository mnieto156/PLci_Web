package es.uned.lsi.PL_ci.entity

import com.fasterxml.jackson.annotation.JsonIgnore

import javax.persistence.*

@Entity
@Table(name = 'curso_alumno')
class CursoAlumno {

    @EmbeddedId
    CursoAlumnoKey id

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId('alumnoId')
    @JoinColumn(name = 'alumno_id')
    @JsonIgnore
    Alumno alumno

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId('cursoId')
    @JoinColumn(name = 'curso_id')
    @JsonIgnore
    Curso curso

    String repositorio

}
