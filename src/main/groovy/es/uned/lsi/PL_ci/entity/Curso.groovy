package es.uned.lsi.PL_ci.entity

import org.hibernate.annotations.Formula

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = 'cursos')
class Curso {
    @Id
    @GeneratedValue
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
