package es.uned.lsi.PL_ci.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.Table
import javax.persistence.GenerationType
import javax.validation.constraints.NotNull
import javax.persistence.Column

@Entity
@Table(name="perfiles")
class Perfil {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name='perfil_id')
	Integer perfilId
	
	@NotNull
	@Column(name='perfil_descripcion',nullable = false)
	String perfilDescripcion
}
