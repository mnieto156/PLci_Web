package es.uned.lsi.PL_ci.repository

import es.uned.lsi.PL_ci.entity.Commit
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommitRepository extends JpaRepository<Commit, Integer> {
	Commit findByCommitId(Integer commit_id)
	
	List<Commit> findByUsuarioUsuarioId(Integer usuario_id)

	List<Commit> findByUsuarioUsuarioId(int usuario_id, Sort sort)
}
