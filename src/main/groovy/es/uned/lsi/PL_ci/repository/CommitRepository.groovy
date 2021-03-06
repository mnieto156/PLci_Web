package es.uned.lsi.PL_ci.repository

import es.uned.lsi.PL_ci.entity.Commit
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommitRepository extends JpaRepository<Commit, Integer> {
	Commit findByCommitId(Integer commitId)

    List<Commit> findByCursoAlumnoAlumnoAlumnoId(Integer alumnoId)

    List<Commit> findByCursoAlumnoAlumnoAlumnoId(Integer alumnoId, Sort sort)

    List<Commit> findByCursoAlumnoCursoNombreAndCursoAlumnoAlumnoAlumnoId(String cursoNombre, Integer alumnoId, Sort sort)
}
