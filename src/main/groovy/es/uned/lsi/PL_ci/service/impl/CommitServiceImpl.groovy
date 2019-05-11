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
	Commit findById(int commit_id) {
		commitRepository.findByCommitId(commit_id)
	}

	@Override
    List<Commit> findByAlumnoAlumnoId(int alumno_id) {
        commitRepository.findByAlumnoAlumnoId(alumno_id)
	}

	@Override
    List<Commit> findByAlumnoAlumnoId(int alumno_id, Sort sort) {
        commitRepository.findByAlumnoAlumnoId(alumno_id, sort)
	}
}
