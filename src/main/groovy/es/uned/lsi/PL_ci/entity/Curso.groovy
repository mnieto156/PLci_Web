package es.uned.lsi.PL_ci.entity

import org.hibernate.annotations.Formula

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = 'cursos')
class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "curso_id_gen")
    @SequenceGenerator(name = "curso_id_gen", sequenceName = "curso_id_seq")
    @Column(name = "curso_id", unique = true, nullable = false)
    int cursoId

    @NotNull
    String anio

    @NotNull
    String asignatura

    @Formula(value = "CONCAT(anio,'-',asignatura)")
    private String nombre

    String getNombre(){
        return nombre
    }

    boolean cerrado = false

    @OneToMany(
            mappedBy = 'curso',
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    Set<CursoAlumno> cursoAlumnos


}