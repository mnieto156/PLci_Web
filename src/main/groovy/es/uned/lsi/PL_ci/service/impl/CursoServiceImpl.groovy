package es.uned.lsi.PL_ci.service.impl

import es.uned.lsi.PL_ci.entity.Curso
import es.uned.lsi.PL_ci.repository.CursoRepository
import es.uned.lsi.PL_ci.service.CursoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CursoServiceImpl implements CursoService {
    @Autowired
    private final CursoRepository cursoRepository

    @Override
    List<Curso> findAll() {
        cursoRepository.findAll().asList()
    }

    @Override
    Curso findById(int id) {
        cursoRepository.findByCursoId(id)
    }

    @Override
    Curso findByNombre(String nombre) {
        cursoRepository.findByNombre(nombre)
    }

    @Override
    List<Curso> findByAnio(String anio) {
        cursoRepository.findByAnio(anio).asList()
    }

    @Override
    List<Curso> findByAsignatura(String asignatura) {
        cursoRepository.findByAsignatura(asignatura).asList()
    }

    @Override
    Curso updateCerrado(int id, boolean cerrado) {
        Curso persisted = findById(id)
        if (persisted){
            persisted.cerrado = cerrado
        cursoRepository.save persisted
        }
        persisted
    }
}
