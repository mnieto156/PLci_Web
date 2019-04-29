package es.uned.lsi.PL_ci.service

import es.uned.lsi.PL_ci.entity.Commit
import org.springframework.data.domain.Sort

interface CommitService {
	Commit findById(int commit_id)
	
	List<Commit> findByUsuarioUsuarioId(int usuario_id)

	List<Commit> findByUsuarioUsuarioId(int usuario_id, Sort sort)
}
