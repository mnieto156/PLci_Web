package es.uned.lsi.PL_ci.controller

import es.uned.lsi.PL_ci.entity.Comentario
import es.uned.lsi.PL_ci.service.ComentarioService
import es.uned.lsi.PL_ci.service.CommitService
import es.uned.lsi.PL_ci.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

import javax.transaction.Transactional

@RestController
@RequestMapping("/comentarios")
@Transactional
@PreAuthorize("isAuthenticated()")
class ComentariosController {

    @Autowired
    ComentarioService comentarioService

    @Autowired
    private final CommitService commitService

    @Autowired
    private final UserService userService

    @GetMapping("findByCommit/{id}")
    List<Comentario> findComentariosByCommitId(@PathVariable Integer id) {
        comentarioService.findByCommitCommitId(id)
    }

    @PostMapping("nuevo")
    @PreAuthorize("#username == principal.username")
    Comentario nuevoComentario(@Param("username") String username, @Param("commitId") Integer commitId, @Param("contenido") String contenido) {
        def usuario = userService.findByIdOrError(username)
        def commit = commitService.findById(commitId)
        if (usuario && commit) {
            def comentario = new Comentario(
                    user: usuario,
                    commit: commit,
                    contenido: contenido
            )
            comentarioService.save comentario
        }
    }
}
