package es.uned.lsi.PL_ci.repository


import es.uned.lsi.PL_ci.entity.Alumno
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
    List<Alumno> findAll()

    Alumno findByAlumnoId(Integer alumno_id)

    Alumno findByUserUsername(String user_id)
}
