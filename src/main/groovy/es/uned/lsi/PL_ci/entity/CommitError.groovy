package es.uned.lsi.PL_ci.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import org.hibernate.annotations.Immutable

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Immutable
@Table(name = "commits_errores")
class CommitError {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = 'error_id')
	Integer errorId

	@NotNull
	@Column(name='error_url',nullable = false)
	String errorUrl
	
	@NotNull
	@Column(name='error_stage_name',nullable = false)
	String errorStageName
	
	@JsonBackReference	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "commit_id", referencedColumnName = "commit_id", nullable = false)
	Commit commit
}
