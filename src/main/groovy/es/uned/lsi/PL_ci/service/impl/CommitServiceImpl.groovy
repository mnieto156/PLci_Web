package es.uned.lsi.PL_ci.service.impl

import es.uned.lsi.PL_ci.entity.Commit
import es.uned.lsi.PL_ci.service.CommitService
import es.uned.lsi.PL_ci.repository.CommitRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class CommitServiceImpl implements CommitService {
	@Autowired
	private final CommitRepository commitRepository

	@Override
	Commit findById(int commitId) {
		commitRepository.findByCommitId(commitId)
	}

	@Override
    List<Commit> findByAlumnoId(int alumnoId) {
        commitRepository.findByCursoAlumnoAlumnoAlumnoId(alumnoId)
	}

	@Override
    List<Commit> findByAlumnoId(int alumnoId, Sort sort) {
        commitRepository.findByCursoAlumnoAlumnoAlumnoId(alumnoId, sort)
	}

	@Override
	List<Commit> findByAlumnoIdCursoNombre(int alumnoId, String cursoNombre, Sort sort) {
		commitRepository.findByCursoAlumnoCursoNombreAndCursoAlumnoAlumnoAlumnoId(cursoNombre,alumnoId,sort)
	}
}
