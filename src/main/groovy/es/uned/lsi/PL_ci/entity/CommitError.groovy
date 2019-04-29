package es.uned.lsi.PL_ci.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.Table
import javax.persistence.GenerationType
import javax.validation.constraints.NotNull
import javax.persistence.FetchType
import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.Column
import javax.persistence.ManyToOne
import javax.persistence.JoinColumn

@Entity
@Table(name="commits_errores")
class CommitError {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name='error_id')
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
