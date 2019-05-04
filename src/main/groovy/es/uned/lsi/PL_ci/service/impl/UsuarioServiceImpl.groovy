package es.uned.lsi.PL_ci.service.impl

import es.uned.lsi.PL_ci.entity.User
import es.uned.lsi.PL_ci.entity.Usuario
import es.uned.lsi.PL_ci.repository.UserRepository
import es.uned.lsi.PL_ci.service.RoleService
import es.uned.lsi.PL_ci.service.UsuarioService
import es.uned.lsi.PL_ci.repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private final UsuarioRepository usuarioRepository

	@Autowired
	RoleService roleService

	@Autowired
	PasswordEncoder passwordEncoder
	
	@Override
	List<Usuario> findAll() {
		usuarioRepository.findAll().asList()
	}

	@Override
	Usuario findById(int usuario_id) {
		usuarioRepository.findByUsuarioId(usuario_id)
	}

	@Override
	Usuario save(Usuario usuario) {
		if (usuario.user==null){
			usuario.user=new User(
					username: usuario.correo.replaceAll('@*',''),
					password: 'changeme'
			)
		}
		usuario.user.password = passwordEncoder.encode(usuario.user.password)
		usuario.user.usuario=usuario
		usuario.user.authorities = [roleService.findByIdOrError("Alumno")]
		usuarioRepository.save(usuario)
	}

	@Override
	Usuario update(Usuario usuario, int usuario_id) {
		Usuario persisted=findById(usuario_id)
		persisted.with {
			nombre		= usuario.nombre
			apellido1	= usuario.apellido1
			apellido2	= usuario.apellido2
			correo		= usuario.correo
			curso		= usuario.curso
		}

		usuarioRepository.save(persisted)
	}

	@Override
	Usuario deleteById(int usuario_id) {
		def usuario=findById(usuario_id)
		usuarioRepository.delete(usuario)
		usuario

	}
}
