package es.uned.lsi.PL_ci.repository

import es.uned.lsi.PL_ci.entity.Alumno
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit4.SpringRunner

import static org.junit.Assert.assertNotNull
import static org.junit.Assert.assertTrue

@RunWith(SpringRunner)
@DataJpaTest
class AlumnoRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager

    @Autowired
    private AlumnoRepository alumnoRepository

    @Test
    void whenFindById_thenReturnAlumno() {
        Alumno testAlumn
        testAlumn = new Alumno(
                nombre: "Nombre",
                apellido1: "Ap1",
                correo: "nomAp1@test.com",
                curso: "2019-2020-PL2")
        alumnoRepository.save testAlumn

        def foundAlumn = alumnoRepository.findByAlumnoId(testAlumn.alumnoId)

        assertNotNull foundAlumn
        assertTrue foundAlumn.nombre == testAlumn.nombre
    }

    @Test
    void findAll() {
        Alumno testAlumn
        testAlumn = new Alumno(
                nombre: "Nombre",
                apellido1: "Ap1",
                correo: "nomAp1@test.com",
                curso: "2019-2020-PL2")
        alumnoRepository.save testAlumn
        def foundAlumns = alumnoRepository.findAll()
        assertTrue foundAlumns.size() == 1
    }


    @After
    void cleanTestDB() {

    }
}