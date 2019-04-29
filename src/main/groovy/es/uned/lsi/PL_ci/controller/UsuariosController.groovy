package es.uned.lsi.PL_ci.controller

import es.uned.lsi.PL_ci.entity.Usuario
import es.uned.lsi.PL_ci.entity.Commit
import es.uned.lsi.PL_ci.service.UsuarioService
import es.uned.lsi.PL_ci.service.CommitService
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.PathVariable

@RestController
@RequestMapping('/usuarios')
class UsuariosController {

    @Autowired
    private final UsuarioService usuarioService

    @Autowired
    private final CommitService commitService

/*	 @RequestMapping(method = RequestMethod.GET)
	 List<Usuario> findAll() {
		 usuarioService.findAll()
	 }*/

/*	 @RequestMapping(value = '/{usuario_id}', method = RequestMethod.GET)
	 Usuario findById(@PathVariable('usuario_id') int usuario_id) {
		 usuarioService.findById(usuario_id)
	 }*/

    /*@RequestMapping(value = '/{usuario_id}/commits', method = RequestMethod.GET)
    List<Commit> findCommitByUsuarioId(@PathVariable('usuario_id') int usuario_id) {
        commitService.findByUsuarioUsuarioId(usuario_id)
    }
    @RequestMapping(value = '/{usuario_id}/commits/{commit_id}',method=RequestMethod.GET)
    Commit findCommitById(@PathVariable('commit_id') int commit_id,@PathVariable('usuario_id') int usuario_id) {
        def commit = commitService.findById(commit_id)
        assert commit.usuario.usuarioId == usuario_id
        return commit
    }*/

    @RequestMapping(value='/lista')
    def listaAlumnos() {
        new ModelAndView(
                "views/listaAlumnos",[alumnos:usuarioService.findAll()])
    }

    @RequestMapping(value = '/{usuario_id}')
    def vistaAlumno(@PathVariable('usuario_id') int usuario_id) {
        new ModelAndView(
                "views/vistaAlumno",[alumno:usuarioService.findById(usuario_id)])
    }

    @RequestMapping(value='/{usuario_id}/commits')
    def listaCommitsAlumno(@PathVariable('usuario_id') int usuario_id) {
        new ModelAndView(
                "views/listaCommits",[alumno:usuarioService.findById(usuario_id),commits:commitService.findByUsuarioUsuarioId(usuario_id, Sort.by(Sort.Direction.DESC,"commitFecha"))]
        )
    }

    @RequestMapping(value = '/{usuario_id}/commits/{commit_id}')
    def vistaCommit(@PathVariable('commit_id') int commit_id,@PathVariable('usuario_id') int usuario_id) {
        def commit = commitService.findById(commit_id)
        if (commit.usuario.usuarioId == usuario_id){
            new ModelAndView(
                    "views/listaErrores", [alumno:usuarioService.findById(usuario_id),commit:commit]
            )
        }

    }
}
