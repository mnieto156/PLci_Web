package es.uned.lsi.PL_ci.repository

import es.uned.lsi.PL_ci.entity.*
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
                correo: "nomAp1@test.com")
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
                correo: "nomAp1@test.com")
        assertThat(alumnoRepository.findAll()).hasSize 1

        alumnoRepository.save new Alumno(
                nombre: "Nombre",
                apellido1: "Ap2",
                correo: "nomAp2@test.com"
        )
        assertThat(alumnoRepository.findAll()).hasSize 2
    }

/*    @Test
    void whenFindByCurso_thenReturnAlumnos() {
        alumnoRepository.save new Alumno(
                nombre: "Nombre",
                apellido1: "Ap1",
                correo: "nomAp1@test.com")

        alumnoRepository.save new Alumno(
                nombre: "Nombre",
                apellido1: "Ap2",
                correo: "nomAp2@test.com"
        )
        alumnoRepository.save new Alumno(
                nombre: "Nombre",
                apellido1: "Ap2",
                correo: "nomAp2@test.com"
        )
        assertThat(alumnoRepository.findAll()).hasSize 3
        assertThat(alumnoRepository.findByCursosAlumnoCursoNombre("2019-2020-PL2")).hasSize 2
    }*/

    @Test
    void whenFindByUsername_thenReturnAlumno() {
        Alumno testAlumn
        testAlumn = new Alumno(
                nombre: "Nombre",
                apellido1: "Ap1",
                correo: "nomAp1@test.com")
        testAlumn.user = new User(
                username: "nomAp1",
                password: "password"
        )
        testAlumn.user.alumno = testAlumn
        alumnoRepository.save testAlumn

        assertThat(alumnoRepository.findByUserUsername(testAlumn.user.username)).isEqualTo testAlumn
    }

    @Test
    void whenFindByCursoID_thenReturnAlumnos() {
        Curso curso = new Curso(
                anio: '2019-2020',
                asignatura: 'PL1',
                cursoAlumnos: new HashSet<CursoAlumno>()
        )
        entityManager.persist curso
        entityManager.flush()
        entityManager.refresh curso
//        Curso persistedCurso = entityManager.find(Curso, curso.cursoId)
        System.out.println("Curso nombre: ${curso.getNombre()}")

        Alumno testAlumn
        testAlumn = new Alumno(
                nombre: "Nombre",
                apellido1: "Ap1",
                correo: "nomAp1@test.com",
                cursosAlumno: new HashSet<CursoAlumno>()
        )
        alumnoRepository.save testAlumn

        CursoAlumno cursoAlumno = new CursoAlumno(
                curso: curso,
                alumno: testAlumn,
                id: new CursoAlumnoKey(cursoId: curso.cursoId, alumnoId: testAlumn.alumnoId)
        )

        testAlumn.cursosAlumno.add cursoAlumno
        curso.cursoAlumnos.add cursoAlumno


        alumnoRepository.save testAlumn

        List<Alumno> alumnos = alumnoRepository.findByCursosAlumnoCursoCursoId curso.cursoId
        assertThat(alumnos.contains(testAlumn)).isTrue()


    }

    @After
    void cleanTestDB() {
        //alumnoRepository.deleteAll()
        //assertThat(alumnoRepository.findAll()).hasSize 0
    }
}