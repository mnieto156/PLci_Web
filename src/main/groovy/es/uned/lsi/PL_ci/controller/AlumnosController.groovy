package es.uned.lsi.PL_ci.controller

import es.uned.lsi.PL_ci.entity.Alumno
import es.uned.lsi.PL_ci.entity.User
import es.uned.lsi.PL_ci.service.AlumnoService
import es.uned.lsi.PL_ci.service.CommitService
import org.springframework.data.domain.Sort
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.servlet.mvc.support.RedirectAttributes

import javax.validation.Valid

@RestController
@RequestMapping('alumnos')
@PreAuthorize('isAuthenticated()')
class AlumnosController {

    @Autowired
    private final AlumnoService alumnoService

    @Autowired
    private final CommitService commitService

/*	 @RequestMapping(method = RequestMethod.GET)
	 List<Alumno> findAll() {
		 alumnoService.findAll()
	 }*/

/*	 @RequestMapping(value = '/{alumno_id}', method = RequestMethod.GET)
	 Alumno findById(@PathVariable('alumno_id') int alumno_id) {
		 alumnoService.findById(alumno_id)
	 }*/

    /*@RequestMapping(value = '/{alumno_id}/commits', method = RequestMethod.GET)
    List<Commit> findCommitByalumnoId(@PathVariable('alumno_id') int alumno_id) {
        commitService.findByalumnoalumnoId(alumno_id)
    }
    @RequestMapping(value = '/{alumno_id}/commits/{commit_id}',method=RequestMethod.GET)
    Commit findCommitById(@PathVariable('commit_id') int commit_id,@PathVariable('alumno_id') int alumno_id) {
        def commit = commitService.findById(commit_id)
        assert commit.alumno.alumnoId == alumno_id
        return commit
    }*/

    @RequestMapping(value = 'lista', method = RequestMethod.GET)
    @PreAuthorize('hasRole("ADMIN")')
    def listaAlumnos(@AuthenticationPrincipal User user) {
        new ModelAndView(
                "views/listaAlumnos", [alumnos: alumnoService.findAll(), userName: user.username])
    }

    @RequestMapping(value = '{alumno_id}')
    @PreAuthorize('hasRole("ROLE_ADMIN")')
// @PreAuthorize('hasRole("ROLE_Admin") or #alumno_id == pincipal.alumno.alumnoId')
    def vistaAlumno(@PathVariable('alumno_id') int alumno_id, @AuthenticationPrincipal User user) {
        new ModelAndView(
                "views/vistaAlumno", [alumno: alumnoService.findById(alumno_id), userName: user.username])
    }

    @RequestMapping(value = "{user_id}/ficha", method = RequestMethod.GET)
    @PreAuthorize('hasRole("ROLE_ADMIN") or #user_id == principal.username')
    def fichaAlumno(@PathVariable String user_id, @AuthenticationPrincipal User user) {
        if (user.authorities.any { it.authority == 'ROLE_ADMIN' }) {
            new ModelAndView("views/fichaAlumnoAdmin", [alumno: alumnoService.findByUserId(user_id), userName: user.username])
        }
        else{
            new ModelAndView("views/fichaAlumno",[userName: user.username, alumno: alumnoService.findByUserId(user_id)])
        }
    }

    @RequestMapping(value = "{user_id}/guardarAlumno", method = RequestMethod.POST)
    def guardarAlumno(@AuthenticationPrincipal User loggedUser,@Valid Alumno alumno, String newPassword, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()){
            new ModelAndView("error",[userName:loggedUser.username])
        }

        if(newPassword != null){
           // userService.updatePassword(newPassword)
        }
        //alumno = alumnoService.update(alumno,alumno.alumnoId)
        redirect.addFlashAttribute("globalMessage","Alumno guardado")
        new ModelAndView("redirect:/{user_id}/ficha","user_id",alumno.user.username)
    }

    @RequestMapping(value = '{alumno_id}/commits')
    def listaCommitsAlumno(@PathVariable('alumno_id') int alumno_id) {
        new ModelAndView(
                "views/listaCommits", [alumno: alumnoService.findById(alumno_id), commits: commitService.findByAlumnoAlumnoId(alumno_id, Sort.by(Sort.Direction.DESC, "commitFecha"))]
        )
    }

    @RequestMapping(value = '{alumno_id}/commits/{commit_id}')
    def vistaCommit(@PathVariable('commit_id') int commit_id, @PathVariable('alumno_id') int alumno_id) {
        def commit = commitService.findById(commit_id)
        if (commit.alumno.alumnoId == alumno_id) {
            new ModelAndView(
                    "views/listaErrores", [alumno: alumnoService.findById(alumno_id), commit: commit]
            )
        }

    }
}
