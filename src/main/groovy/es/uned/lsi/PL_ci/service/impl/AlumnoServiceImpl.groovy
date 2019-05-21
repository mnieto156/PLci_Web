package es.uned.lsi.PL_ci.service.impl


import es.uned.lsi.PL_ci.entity.User
import es.uned.lsi.PL_ci.entity.Alumno
import es.uned.lsi.PL_ci.service.RoleService
import es.uned.lsi.PL_ci.service.AlumnoService
import es.uned.lsi.PL_ci.repository.AlumnoRepository
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
	PasswordEncoder passwordEncoder
	
	@Override
	List<Alumno> findAll() {
		alumnoRepository.findAll().asList()
	}

	@Override
	Alumno findById(int alumno_id) {
		alumnoRepository.findByAlumnoId(alumno_id)
	}

	@Override
	Alumno findByUserId(String user_id) {
		alumnoRepository.findByUserUsername(user_id)
	}

	@Override
	Alumno save(Alumno alumno) {
		if (alumno.user == null) {
			alumno.user = new User(
					username: alumno.correo.replaceAll('@*', ''),
					password: 'changeme'
			)
		}
		alumno.user.password = passwordEncoder.encode(alumno.user.password)
		alumno.user.alumno = alumno
		alumno.user.authorities = [roleService.findByIdOrError("Alumno")]
		alumnoRepository.save(alumno)
	}

	@Override
	Alumno update(Alumno alumno, int alumno_id) {
		Alumno persisted = findById(alumno_id)
		persisted.with {
			nombre = alumno.nombre ?: nombre
			apellido1 = alumno.apellido1 ?: apellido1
			apellido2 = alumno.apellido2
			correo = alumno.correo ?: correo
			curso = alumno.curso ?: curso
			repositorio = alumno.repositorio ?: repositorio
		}

		alumnoRepository.save(persisted)
	}

	@Override
	Alumno deleteById(int alumno_id) {
		def alumno = findById(alumno_id)
		alumnoRepository.delete(alumno)
		alumno

	}
}
