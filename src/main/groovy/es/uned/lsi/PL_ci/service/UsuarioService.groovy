package es.uned.lsi.PL_ci.service

import es.uned.lsi.PL_ci.entity.Usuario

interface UsuarioService {
	List<Usuario> findAll()
	
	Usuario findById(int usuario_id)

	Usuario save(Usuario usuario)

	Usuario update(Usuario usuario, int usuario_id)

	Usuario deleteById(int usuario_id)
}
