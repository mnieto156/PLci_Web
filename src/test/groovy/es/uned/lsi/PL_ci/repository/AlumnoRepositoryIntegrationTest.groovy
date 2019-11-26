package es.uned.lsi.PL_ci.repository

import es.uned.lsi.PL_ci.entity.Alumno
import es.uned.lsi.PL_ci.entity.User
import org.junit.After
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
        assertThat(foundAlumn).isEqualTo testAlumn
    }

    @Test
    void whenFindAll_thenReturnAlumnos() {

        alumnoRepository.save new Alumno(
                nombre: "Nombre",
                apellido1: "Ap1",
                correo: "nomAp1@test.com",
                curso: "2019-2020-PL2")
        assertThat(alumnoRepository.findAll()).hasSize 1

        alumnoRepository.save new Alumno(
                nombre: "Nombre",
                apellido1: "Ap2",
                correo: "nomAp2@test.com",
                curso: "2019-2020-PL2"
        )
        assertThat(alumnoRepository.findAll()).hasSize 2
    }

    @Test
    void whenFindByCurso_thenReturnAlumnos() {
        alumnoRepository.save new Alumno(
                nombre: "Nombre",
                apellido1: "Ap1",
                correo: "nomAp1@test.com",
                curso: "2019-2020-PL2")

        alumnoRepository.save new Alumno(
                nombre: "Nombre",
                apellido1: "Ap2",
                correo: "nomAp2@test.com",
                curso: "2019-2020-PL2"
        )
        alumnoRepository.save new Alumno(
                nombre: "Nombre",
                apellido1: "Ap2",
                correo: "nomAp2@test.com",
                curso: "2019-2020-PL1"
        )
        assertThat(alumnoRepository.findAll()).hasSize 3
        assertThat(alumnoRepository.findByCurso("2019-2020-PL2")).hasSize 2
    }

    @Test
    void whenFindByUsername_thenReturnAlumno() {
        Alumno testAlumn
        testAlumn = new Alumno(
                nombre: "Nombre",
                apellido1: "Ap1",
                correo: "nomAp1@test.com",
                curso: "2019-2020-PL2")
        testAlumn.user = new User(
                username: "nomAp1",
                password: "password"
        )
        testAlumn.user.alumno = testAlumn
        alumnoRepository.save testAlumn

        assertThat(alumnoRepository.findByUserUsername(testAlumn.user.username)).isEqualTo testAlumn
    }

    @After
    void cleanTestDB() {
        //alumnoRepository.deleteAll()
        //assertThat(alumnoRepository.findAll()).hasSize 0
    }
}