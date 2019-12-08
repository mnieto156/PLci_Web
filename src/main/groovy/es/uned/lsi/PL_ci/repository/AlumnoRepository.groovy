package es.uned.lsi.PL_ci.repository


import es.uned.lsi.PL_ci.entity.Alumno
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
    List<Alumno> findAll()

    List<Alumno> findByCurso(String curso)

    Alumno findByAlumnoId(Integer alumnoId)

    Alumno findByUserUsername(String userId)

    List<Alumno> findByCursosAlumnoCursoCursoId(Integer cursoId)

    List<Alumno> findByCursosAlumnoCursoNombre(String cursoNombre)
}
