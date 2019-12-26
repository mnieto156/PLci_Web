package es.uned.lsi.PL_ci.repository

import es.uned.lsi.PL_ci.entity.Curso
import es.uned.lsi.PL_ci.entity.CursoAlumno
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit4.SpringRunner
import static org.assertj.core.api.Assertions.assertThat
import static org.junit.Assert.assertNotNull

@RunWith(SpringRunner)
@DataJpaTest
class CursoRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager

    @Autowired
    CursoRepository cursoRepository

    @Test
    void whenNewCurso_thenFindByNombre(){
        Curso curso = new Curso(
                anio: '2019-2020',
                asignatura: 'PL1',
                cursoAlumnos: new HashSet<CursoAlumno>()
        )

        cursoRepository.saveAndFlush curso

        entityManager.refresh curso
        def foundCurso = cursoRepository.findByNombre "${curso.anio}-${curso.asignatura}"

        assertNotNull foundCurso
        assertThat(foundCurso).isEqualTo curso

        System.out.println("Curso nombre: ${foundCurso.getNombre()}")

    }
}
