package es.uned.lsi.PL_ci.entity

import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.MapsId
import javax.persistence.Table

@Entity
@Table(name = 'curso_alumno')
class CursoAlumno {

    @EmbeddedId
    CursoAlumnoKey id

    @ManyToOne
    @MapsId('alumno_id')
    @JoinColumn(name = 'alumno_id')
    Alumno alumno

    @ManyToOne
    @MapsId('curso_id')
    @JoinColumn(name = 'curso_id')
    Curso curso

    String repositorio

}
