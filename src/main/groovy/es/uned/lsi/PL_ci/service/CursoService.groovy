package es.uned.lsi.PL_ci.service

import es.uned.lsi.PL_ci.entity.Curso

interface CursoService {

    List<Curso> findAll()

    Curso findById(int id)

    Curso findByNombre(String nombre)

    List<Curso> findByAnio(String anio)

    List<Curso> findByAsignatura(String asignatura)

    Curso updateCerrado(int id, boolean cerrado)

    Curso save(Curso curso)
}