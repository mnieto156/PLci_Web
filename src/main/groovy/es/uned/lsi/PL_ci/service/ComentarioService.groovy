package es.uned.lsi.PL_ci.service

import es.uned.lsi.PL_ci.entity.Comentario

interface ComentarioService {

    List<Comentario> findByCommitCommitId(Integer id)

    Comentario save(Comentario comentario)
}