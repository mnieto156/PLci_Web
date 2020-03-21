package es.uned.lsi.PL_ci.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import org.hibernate.annotations.Immutable

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Immutable
@Table(name = "commits")
class Commit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = 'commit_id')
    Integer commitId

    @NotNull
    @Column(name = "commit_url", nullable = false)
    String commitUrl

    @NotNull
    @Column(name = "commit_fecha", nullable = false)
    Date commitFecha

    @NotNull
    @Column(name = "commit_num_errores", nullable = false)
    Integer commitNumErrores

    @NotNull
    @Column(name = "commit_num_correctos", nullable = false)
    Integer commitNumCorrectos

/*	@ManyToOne
	@JoinColumn(name = "alumno_id", referencedColumnName = "alumno_id", nullable = false)
	Alumno alumno

	@ManyToOne
	@JoinColumn(name = "curso_id", referencedColumnName = "curso_id", nullable = false)
	Curso curso*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns(value = [
            @JoinColumn(name = "alumno_id", referencedColumnName = "alumno_id", updatable = false, insertable = false),
            @JoinColumn(name = "curso_id", referencedColumnName = "curso_id", updatable = false, insertable = false)
    ])
    CursoAlumno cursoAlumno

    @JsonManagedReference
    @OneToMany(mappedBy = "commit")
    List<CommitError> commitErrors

    @JsonManagedReference
    @OneToMany(mappedBy = "commit")
    Set<Comentario> comentarios = new HashSet<Comentario>()
}
