package es.uned.lsi.PL_ci.entity

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.Table
import javax.persistence.GenerationType
import javax.validation.constraints.NotNull
import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.Column
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.JoinColumn

@Entity
@Table(name="commits")
class Commit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name='commit_id')
	Integer commitId
	
	@NotNull
	@Column(name = "commit_url",nullable = false)
	String commitUrl
	
	@NotNull
	@Column(name = "commit_fecha",nullable = false)
	Date commitFecha
	
	@NotNull
	@Column(name = "commit_num_errores",nullable = false)
	Integer commitNumErrores
	
	@NotNull
	@Column(name = "commit_num_correctos",nullable = false)
	Integer commitNumCorrectos
	
	@ManyToOne
	@JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id", nullable = false)
	Usuario usuario
	@JsonManagedReference
	@OneToMany(mappedBy = "commit",fetch = FetchType.LAZY)
	List<CommitError> commitErrors
}
