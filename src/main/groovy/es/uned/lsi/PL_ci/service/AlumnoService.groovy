package es.uned.lsi.PL_ci.service


import es.uned.lsi.PL_ci.entity.Alumno

interface AlumnoService {
    List<Alumno> findAll()

    List<Alumno> findByCurso(String curso)

    Alumno findById(int alumno_id)

    Alumno findByUserId(String id)

    Alumno save(Alumno alumno)

    Alumno update(Alumno alumno, int alumno_id)

    Alumno deleteById(int alumno_id)
}
