package es.uned.lsi.PL_ci.controller

import es.uned.lsi.PL_ci.entity.Curso
import es.uned.lsi.PL_ci.entity.User
import es.uned.lsi.PL_ci.service.CursoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView

@RestController
@RequestMapping('cursos')
@PreAuthorize('isAuthenticated() and hasRole("ADMIN")')
class CursosController {

    @Autowired
    private final CursoService cursoService


    @GetMapping('lista')
    List<Curso> findAll() {
        cursoService.findAll()
    }

    @GetMapping('{cursoNombre}')
    Curso getCursoByNombre(@PathVariable String cursoNombre) {
        cursoService.findByNombre cursoNombre
    }

    @RequestMapping('nuevo')
    def nuevoCurso(@AuthenticationPrincipal User user) {
        new ModelAndView('views/fichaCurso', [userName: user.username])
    }

    @RequestMapping('nuevo/guardarCurso')
    def guardarCurso(@AuthenticationPrincipal User user, String cursoAnio, String cursoAsignatura) {
        def curso = new Curso(anio: cursoAnio, asignatura: cursoAsignatura)
        cursoService.save curso
        new ModelAndView('redirect:lista')
    }
}
