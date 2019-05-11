package es.uned.lsi.PL_ci.service

import es.uned.lsi.PL_ci.entity.Commit
import org.springframework.data.domain.Sort

interface CommitService {
	Commit findById(int commit_id)

    List<Commit> findByAlumnoAlumnoId(int alumno_id)

    List<Commit> findByAlumnoAlumnoId(int alumno_id, Sort sort)
}
