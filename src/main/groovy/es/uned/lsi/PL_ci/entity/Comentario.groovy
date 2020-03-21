package es.uned.lsi.PL_ci.entity

import com.fasterxml.jackson.annotation.JsonBackReference

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "comentarios")
class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coment_id_gen")
    @SequenceGenerator(name = "coment_id_gen", sequenceName = "coment_id_seq", allocationSize = 1)
    Integer comentarioId

    @NotNull
    String contenido

    @Column(insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Date comentFecha

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
    User user

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commit_id", referencedColumnName = "commit_id", nullable = false)
    Commit commit

    @Transient
    String getUsername() {
        user.username
    }
}
