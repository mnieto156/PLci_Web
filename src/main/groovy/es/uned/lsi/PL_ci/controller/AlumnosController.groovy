package es.uned.lsi.PL_ci.controller

import es.uned.lsi.PL_ci.entity.Alumno
import es.uned.lsi.PL_ci.entity.User
import es.uned.lsi.PL_ci.service.AlumnoService
import es.uned.lsi.PL_ci.service.CommitService
import es.uned.lsi.PL_ci.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.data.web.SortDefault
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

    @RequestMapping(value = '{userId}/avances')
    //@PreAuthorize('hasRole("ROLE_ADMIN")')
    @PreAuthorize('hasRole("ADMIN") or #userId == principal.username')
    def vistaAlumno(@PathVariable('userId') String userId, @AuthenticationPrincipal User user) {
        new ModelAndView(
                "views/vistaAlumno", [alumno: alumnoService.findByUserId(userId), userName: user.username])
    }

    @RequestMapping(value = "{userId}/ficha", method = RequestMethod.GET)
    @PreAuthorize('hasRole("ROLE_ADMIN") or #userId == principal.username')
    def fichaAlumno(@PathVariable String userId, @AuthenticationPrincipal User user) {
        if (user.authorities.any { it.authority == 'ROLE_ADMIN' }) {
            new ModelAndView("views/fichaAlumnoAdmin", [alumno: alumnoService.findByUserId(userId), userName: user.username])
        }
        else{
            new ModelAndView("views/fichaAlumno",[userName: user.username, alumno: alumnoService.findByUserId(userId)])
        }
    }

    @RequestMapping(value = 'nuevo/ficha')
    @PreAuthorize('hasRole("ADMIN")')
    def nuevoAlumno(@AuthenticationPrincipal User user) {
        new ModelAndView("views/fichaAlumnoAdmin", [alumno: new Alumno(), userName: user.username])
    }

    @RequestMapping(value = "{userId}/guardarAlumnoAdmin", method = RequestMethod.POST)
    @PreAuthorize('hasRole("ADMIN")')
    def guardarAlumnoAdmin(@PathVariable String userId, @AuthenticationPrincipal User loggedUser, @Valid Alumno alumno, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            new ModelAndView("views/error", [userName: loggedUser.username, errors: result.allErrors])
        }
        if (userId == 'nuevo') {
            alumno = alumnoService.save alumno
        } else {
            alumno = alumnoService.update alumno, alumno.alumnoId
        }


        redirect.addFlashAttribute("globalMessage", "Cambios guardados")
        new ModelAndView("redirect:ficha", "userId", alumno.user.username)
    }

    @RequestMapping(value = "{userId}/guardarAlumno", method = RequestMethod.POST)
    @PreAuthorize('#userId == principal.username')
    def guardarAlumno(String newPassword, String oldPassword, @PathVariable String userId, @AuthenticationPrincipal User loggedUser, Alumno alumno, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()){
            new ModelAndView("views/error", [userName: loggedUser.username, errors: result.allErrors])
        }
        alumno = alumnoService.update alumno, alumno.alumnoId
        if (newPassword != null && passwordEncoder.matches(oldPassword, alumno.user?.password)) {
            userService.updatePassword alumno.user.username, newPassword
        }
        redirect.addFlashAttribute("globalMessage", "Cambios guardados")
        new ModelAndView("redirect:ficha", "userId", userId)
    }

    @RequestMapping(value = '{userId}/commits')
    @PreAuthorize('hasRole("ADMIN") or #userId == principal.username')
    def listaCommitsAlumno(@PathVariable('userId') String userId, @AuthenticationPrincipal User loggedUser, @SortDefault(sort="commitFecha",direction = Sort.Direction.DESC) Sort sort) {
        def alumno = alumnoService.findByUserId(userId)
        new ModelAndView(
                "views/listaCommits", [alumno: alumno,
                                                commits: commitService.findByAlumnoId(alumno.alumnoId, sort),
                                                userName:loggedUser.username]
        )
    }

    @RequestMapping(value = '{userId}/commits/{commitId}')
    @PreAuthorize('hasRole("ADMIN") or #userId == principal.username')
    def vistaCommit(@PathVariable('commitId') int commitId, @PathVariable('userId') String userId, @AuthenticationPrincipal User loggedUser) {
        def commit = commitService.findById(commitId)
        def alumno = alumnoService.findByUserId(userId)
        if (commit.cursoAlumno.alumno.alumnoId == alumno.alumnoId) {
            new ModelAndView(
                    "views/listaErrores", [alumno: alumno, commit: commit, userName:loggedUser.username]
            )
        }
    }
    @RequestMapping(value = '{userId}/{curso}/commits')
    @PreAuthorize('hasRole("ADMIN") or #userId == principal.username')
    def listaCommitsAlumnoCurso(@PathVariable String userId,@PathVariable String curso, @AuthenticationPrincipal User loggedUser) {
        def alumno = alumnoService.findByUserId(userId)
        new ModelAndView(
                "views/listaCommits", [alumno: alumno,
                                       commits: commitService.findByAlumnoIdCursoNombre(alumno.alumnoId,curso),
                                       userName:loggedUser.username]
        )
    }
}
