package es.uned.lsi.PL_ci.entity

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.SequenceGenerator
import javax.validation.constraints.NotNull


@Entity
class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coment_id_gen")
    @SequenceGenerator(name = "coment_id_gen", sequenceName = "coment_id_seq")
    Integer comentarioId

    @NotNull
    String contenido

    @NotNull
    Date comentFecha

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commit_id", referencedColumnName = "commit_id", nullable = false)
    Commit commit
}
