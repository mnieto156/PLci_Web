package es.uned.lsi.PL_ci.service.impl

import es.uned.lsi.PL_ci.entity.Comentario
import es.uned.lsi.PL_ci.repository.ComentarioRepository
import es.uned.lsi.PL_ci.service.ComentarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ComentarioServiceImpl implements ComentarioService {

    @Autowired
    private final ComentarioRepository comentarioRepository

    @Override
    List<Comentario> findByCommitCommitId(Integer id) {
        return comentarioRepository.findByCommitCommitId(id)
    }

    @Override
    Comentario save(Comentario comentario) {
        if (comentario.user && comentario.commit) {
            comentario.contenido = comentario.username + ': ' + comentario.contenido
            comentario.user.comentarios.add comentario
            comentario.commit.comentarios.add comentario
            comentarioRepository.save(comentario)
        } else null
    }
}
