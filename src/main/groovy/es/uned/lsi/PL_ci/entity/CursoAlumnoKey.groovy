package es.uned.lsi.PL_ci.entity

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class CursoAlumnoKey implements Serializable{

    @Column(name = 'alumno_id')
    int alumnoId

    @Column(name = 'curso_id')
    int cursoId

    @Override
    int hashCode(){
        final int prime = 31
        int result = 1
        result = prime * result + (alumnoId ? 0 : alumnoId.hashCode())
        result = prime * result + (cursoId ? 0 : cursoId.hashCode())
        return result
    }

    @Override
    boolean equals(Object obj){
        if (this == obj)
            return true
        if (obj == null)
            return false
        if (getClass() != obj.getClass())
            return false
        CursoAlumnoKey other = (CursoAlumnoKey) obj
        if (!cursoId) {
            if (other.cursoId )
                return false
        } else if (cursoId != other.cursoId)
            return false
        if (!alumnoId) {
            if (other.alumnoId )
                return false
        } else if (alumnoId != other.alumnoId)
            return false
        return true
    }
}
