package es.uned.lsi.PL_ci.controller

import es.uned.lsi.PL_ci.entity.Alumno
import es.uned.lsi.PL_ci.entity.User
import es.uned.lsi.PL_ci.service.AlumnoService
import es.uned.lsi.PL_ci.service.CommitService
import es.uned.lsi.PL_ci.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView
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

    @Autowired
    private final UserService userService

    @Autowired
    private final PasswordEncoder passwordEncoder

    @RequestMapping(value = 'lista', method = RequestMethod.GET)
    @PreAuthorize('hasRole("ADMIN")')
    def listaAlumnos(@AuthenticationPrincipal User user) {
        new ModelAndView(
                "views/listaAlumnos", [alumnos: alumnoService.findAll(), userName: user.username])
    }

    @RequestMapping(value = 'lista/{curso}', method = RequestMethod.GET)
    @PreAuthorize('hasRole("ADMIN")')
    def listaAlumnosCurso(@PathVariable('curso') String curso, @AuthenticationPrincipal User user) {
        new ModelAndView(
                "views/listaAlumnos", [alumnos: alumnoService.findByCurso(curso), userName: user.username])
    }

    @RequestMapping(value = '{alumno_id}')
    @PreAuthorize('hasRole("ROLE_ADMIN")')
// @PreAuthorize('hasRole("ROLE_Admin") or #alumno_id == principal.alumno.alumnoId')
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

    @RequestMapping(value = 'nuevo/ficha')
    @PreAuthorize('hasRole("ADMIN")')
    def nuevoAlumno(@AuthenticationPrincipal User user) {
        new ModelAndView("views/fichaAlumnoAdmin", [alumno: new Alumno(), userName: user.username])
    }

    @RequestMapping(value = "{user_id}/guardarAlumnoAdmin", method = RequestMethod.POST)
    @PreAuthorize('hasRole("ADMIN")')
    def guardarAlumnoAdmin(@PathVariable String user_id, @AuthenticationPrincipal User loggedUser, @Valid Alumno alumno, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            new ModelAndView("views/error", [userName: loggedUser.username, errors: result.allErrors])
        }
        if (user_id == 'nuevo') {
            alumno = alumnoService.save alumno
        } else {
            alumno = alumnoService.update alumno, alumno.alumnoId
        }


        redirect.addFlashAttribute("globalMessage", "Cambios guardados")
        new ModelAndView("redirect:ficha", "user_id", alumno.user.username)
    }

    @RequestMapping(value = "{user_id}/guardarAlumno", method = RequestMethod.POST)
    @PreAuthorize('#user_id == principal.username')
    def guardarAlumno(String newPassword, String oldPassword, @PathVariable String user_id, @AuthenticationPrincipal User loggedUser, Alumno alumno, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()){
            new ModelAndView("views/error", [userName: loggedUser.username, errors: result.allErrors])
        }
        alumno = alumnoService.update alumno, alumno.alumnoId
        if (newPassword != null && passwordEncoder.matches(oldPassword, alumno.user?.password)) {
            userService.updatePassword alumno.user.username, newPassword
        }
        redirect.addFlashAttribute("globalMessage", "Cambios guardados")
        new ModelAndView("redirect:ficha", "user_id", user_id)
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
