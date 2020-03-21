package es.uned.lsi.PL_ci.repository

import es.uned.lsi.PL_ci.entity.Comentario
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ComentarioRepository extends CrudRepository<Comentario, Integer> {

    List<Comentario> findByCommitCommitId(Integer id)

}