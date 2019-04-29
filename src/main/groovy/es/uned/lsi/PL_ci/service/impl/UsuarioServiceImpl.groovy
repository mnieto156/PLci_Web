package es.uned.lsi.PL_ci.service.impl

import es.uned.lsi.PL_ci.entity.Usuario
import es.uned.lsi.PL_ci.service.UsuarioService
import es.uned.lsi.PL_ci.repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private final UsuarioRepository usuarioRepository
	
	@Override
	List<Usuario> findAll() {
		usuarioRepository.findAll()
	}

	@Override
	Usuario findById(int usuario_id) {
		usuarioRepository.findByUsuarioId(usuario_id)
	}
}
