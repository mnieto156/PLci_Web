package es.uned.lsi.PL_ci.repository

import es.uned.lsi.PL_ci.entity.Comentario
import org.springframework.data.repository.CrudRepository

interface ComentarioRepository extends CrudRepository<Comentario, Integer> {

    List<Comentario> findByCommitCommitId(Integer id)

}