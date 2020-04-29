package es.uned.lsi.PL_ci.service.impl

import es.uned.lsi.PL_ci.entity.Curso
import es.uned.lsi.PL_ci.entity.CursoAlumno
import es.uned.lsi.PL_ci.repository.CursoRepository
import es.uned.lsi.PL_ci.restClient.GiteaRepo
import es.uned.lsi.PL_ci.restClient.GiteaUser
import es.uned.lsi.PL_ci.service.CursoService
import es.uned.lsi.PL_ci.service.GiteaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CursoServiceImpl implements CursoService {
    @Autowired
    private final CursoRepository cursoRepository

    @Autowired
    GiteaService giteaService

    @Override
    List<Curso> findAll() {
        cursoRepository.findAll().asList()
    }

    @Override
    Curso findById(int id) {
        cursoRepository.findByCursoId(id)
    }

    @Override
    Curso findByNombre(String nombre) {
        cursoRepository.findByNombre(nombre)
    }

    @Override
    List<Curso> findByAnio(String anio) {
        cursoRepository.findByAnio(anio).asList()
    }

    @Override
    List<Curso> findByAsignatura(String asignatura) {
        cursoRepository.findByAsignatura(asignatura).asList()
    }

    @Override
    Curso updateCerrado(int id, boolean cerrado) {
        Curso persisted = findById(id)
        if (persisted) {
            persisted.cerrado = cerrado
            cursoRepository.save persisted
            //ToDo: cerrar repositorios Gitea
            def repoList = giteaService.getAllReposOfUser(persisted.nombre).block() // comprobar que hay repos
            repoList.each { repo ->
                repo.archived = cerrado
                giteaService.updateRepo(repo,persisted.nombre).block()
            }
        }
        persisted
    }

    @Override
    Curso save(Curso curso) {
        def nombreCurso = curso.anio + '-' + curso.asignatura
        Curso persisted = findByNombre(nombreCurso)
        if (!persisted) {
            if (!curso.cursoAlumnos) {
                curso.cursoAlumnos = new HashSet<CursoAlumno>()
            }
            def giteaUser = new GiteaUser(
                    username: nombreCurso,
                    password: curso.asignatura + '.Admin_' + curso.anio,
                    email: 'adminpl@uned.es')
            giteaService.addUser(giteaUser).block()
            //ToDo: crear repositorio base de Gitea?
            // - No puede hacerse fork de un repositorio propio!!

            def giteaBaseRepo = new GiteaRepo(name:'base', description: "Repositorio base del curso ${nombreCurso}")
            giteaBaseRepo.private=true
            //giteaBaseRepo.template=true // repositorio como template no expuesto al api :(
            giteaBaseRepo = giteaService.addRepo(giteaBaseRepo,nombreCurso).block()
            curso.baseRepository = giteaBaseRepo.html_url
            cursoRepository.save curso
        } else persisted
    }
}
