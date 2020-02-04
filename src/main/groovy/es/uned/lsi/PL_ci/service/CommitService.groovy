package es.uned.lsi.PL_ci.service

import es.uned.lsi.PL_ci.entity.Commit
import org.springframework.data.domain.Sort

interface CommitService {
	Commit findById(int commitId)

    List<Commit> findByAlumnoId(int alumnoId)

    List<Commit> findByAlumnoId(int alumnoId, Sort sort)

    List<Commit> findByAlumnoIdCursoNombre(int alumnoId, String cursoNombre, Sort sort)
}
