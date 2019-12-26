package es.uned.lsi.PL_ci.controller

import es.uned.lsi.PL_ci.entity.Curso
import es.uned.lsi.PL_ci.service.CursoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping('cursos')
@PreAuthorize('isAuthenticated() and hasRole("ADMIN")')
class CursosController {

    @Autowired
    private final CursoService cursoService


    @GetMapping
    List<Curso> findAll(){
        cursoService.findAll()
    }

    @GetMapping('{cursoNombre}')
    Curso getCursoByNombre(@PathVariable String cursoNombre){
        cursoService.findByNombre cursoNombre
    }
 
}
