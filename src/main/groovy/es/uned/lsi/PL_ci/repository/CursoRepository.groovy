package es.uned.lsi.PL_ci.repository

import es.uned.lsi.PL_ci.entity.Curso
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CursoRepository extends JpaRepository<Curso,String>{

    List<Curso> findAll()

    Curso findByCursoId(int cursoId)

    Curso findByNombre(String nombre)

    List<Curso> findByAnio(String anio)

    List<Curso> findByAsignatura(String asignatura)

}