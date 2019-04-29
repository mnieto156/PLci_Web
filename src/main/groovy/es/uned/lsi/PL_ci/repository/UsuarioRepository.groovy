package es.uned.lsi.PL_ci.repository

import es.uned.lsi.PL_ci.entity.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	List<Usuario> findAll()
	
	Usuario findByUsuarioId(Integer usuario_id)
}
