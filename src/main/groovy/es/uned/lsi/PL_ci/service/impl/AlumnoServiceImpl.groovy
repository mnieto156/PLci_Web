package es.uned.lsi.PL_ci.service.impl

import es.uned.lsi.PL_ci.config.AppConfig
import es.uned.lsi.PL_ci.entity.*
import es.uned.lsi.PL_ci.repository.AlumnoRepository
import es.uned.lsi.PL_ci.restClient.GiteaRepo
import es.uned.lsi.PL_ci.restClient.GiteaUser
import es.uned.lsi.PL_ci.service.AlumnoService
import es.uned.lsi.PL_ci.service.CursoService
import es.uned.lsi.PL_ci.service.GiteaService
import es.uned.lsi.PL_ci.service.RoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    private final AlumnoRepository alumnoRepository

    @Autowired
    RoleService roleService

    @Autowired
    CursoService cursoService

    @Autowired
    GiteaService giteaService

    @Autowired
    PasswordEncoder passwordEncoder

    @Autowired
    AppConfig appConfig

    @Override
    List<Alumno> findAll() {
        alumnoRepository.findAll().asList()
    }

    @Override
    List<Alumno> findByCurso(String curso) {
        alumnoRepository.findByCursosAlumnoCursoNombre(curso)
    }

    @Override
    Alumno findById(int alumnoId) {
        alumnoRepository.findByAlumnoId(alumnoId)
    }

    @Override
    Alumno findByUserId(String userId) {
        alumnoRepository.findByUserUsername(userId)
    }

    @Override
    Alumno save(Alumno alumno) {
        if (!alumno.user ) {
            def user = new User(
                    username: alumno.correo.replaceAll('@.*', ''),
                    password: 'changeMe.1234'
            )
            alumno.user = user
            def giteaUser = new GiteaUser(
                    username: user.username,
                    password: user.password,
                    email: alumno.correo )
            giteaUser = giteaService.addUser(giteaUser).block()
            giteaUser.allow_create_organization = false
            giteaUser.max_repo_creation = 0
            giteaService.updateUser(giteaUser).block()
        }
        alumno.user.password = passwordEncoder.encode(alumno.user.password)
        alumno.user.alumno = alumno
        alumno.user.authorities = [roleService.findByIdOrError("ROLE_ALUMNO")]
        alumnoRepository.save alumno
    }

    @Override
    Alumno update(Alumno alumno, int alumnoId) {
        Alumno persisted = findById alumnoId
        persisted.with {
            nombre = alumno.nombre ?: nombre
            apellido1 = alumno.apellido1 ?: apellido1
            apellido2 = alumno.apellido2
            correo = alumno.correo ?: correo
        }

        alumnoRepository.save persisted
    }

    @Override
    Alumno deleteById(int alumnoId) {
        def alumno = findById alumnoId
        alumnoRepository.delete alumno
        alumno
    }

    @Override
    Alumno addCurso(int alumnoId, String nombreCurso) {
        Curso curso = cursoService.findByNombre nombreCurso
        Alumno alumno = findById alumnoId

        if (curso){

            def cursoAlumno = new CursoAlumno(
                    curso: curso,
                    alumno: alumno,
                    id: new CursoAlumnoKey(cursoId: curso.cursoId, alumnoId: alumno.alumnoId),
                    repositorio: "${appConfig.getGitea().baseurl}/${curso.nombre}/${alumno.user.username}.git"
            )
            def found = alumno.cursosAlumno.find { it.id.cursoId == cursoAlumno.id.cursoId }
            if (!found) {
                def giteaRepo = new GiteaRepo(name: alumno.user.username)
                giteaRepo.setPrivate(true)
                giteaRepo = giteaService.addRepo(giteaRepo, curso.nombre).block()
                giteaService.addCollaboratorToRepo(giteaRepo.name, curso.nombre, alumno.user.username).block()
                cursoAlumno.repositorio = "${giteaRepo.html_url}.git"
                alumno.cursosAlumno.add(cursoAlumno)
                curso.cursoAlumnos.add(cursoAlumno)
                alumno = alumnoRepository.save alumno

            }
        }
        alumno
    }
}
