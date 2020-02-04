package es.uned.lsi.PL_ci.entity

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class CursoAlumnoKey implements Serializable{

    @Column(name = 'alumno_id')
    Integer alumnoId

    @Column(name = 'curso_id')
    Integer cursoId

    @Override
    int hashCode(){
        Objects.hash(alumnoId, cursoId)
    }

    @Override
    boolean equals(Object obj) {
        if (this == obj)
            return true
        if (obj == null)
            return false
        if (getClass() != obj.getClass())
            return false
        CursoAlumnoKey other = (CursoAlumnoKey) obj
        return Objects.equals(alumnoId, other.alumnoId) &&
                Objects.equals(cursoId, other.cursoId)
    }
}
