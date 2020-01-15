package es.uned.lsi.PL_ci.service.impl

import es.uned.lsi.PL_ci.entity.Alumno
import es.uned.lsi.PL_ci.entity.Curso
import es.uned.lsi.PL_ci.entity.CursoAlumno
import es.uned.lsi.PL_ci.entity.CursoAlumnoKey
import es.uned.lsi.PL_ci.entity.User
import es.uned.lsi.PL_ci.repository.AlumnoRepository
import es.uned.lsi.PL_ci.service.AlumnoService
import es.uned.lsi.PL_ci.service.CursoService
import es.uned.lsi.PL_ci.service.RoleService
import es.uned.lsi.PL_ci.service.UserService
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
    UserService userService

    @Autowired
    CursoService cursoService

    @Autowired
    PasswordEncoder passwordEncoder

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
            alumno.user = new User(
                    username: alumno.correo.replaceAll('@.*', ''),
                    password: 'changeme'
            )
        }
        alumno.user.password = passwordEncoder.encode(alumno.user.password)
        alumno.user.alumno = alumno
        alumno.user.authorities = [roleService.findByIdOrError("Alumno")]
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
            curso = alumno.curso ?: curso
            repositorio = alumno.repositorio ?: repositorio
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
        if (!alumno?.cursosAlumno){
            alumno.cursosAlumno = new HashSet<CursoAlumno>()
        }
        if (curso?.cursoAlumnos ){
            def cursoAlumno = new CursoAlumno(
                    curso: curso,
                    alumno: alumno,
                    id: new CursoAlumnoKey(cursoId: curso.cursoId, alumnoId: alumno.alumnoId),
                    repositorio: "${curso.nombre}/${alumno.user.username}"
            )
            if (alumno.cursosAlumno.add(cursoAlumno) && curso.cursoAlumnos.add(cursoAlumno)){
                alumnoRepository.save alumno
            }
        }
        alumno
    }
}
