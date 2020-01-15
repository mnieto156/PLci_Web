package es.uned.lsi.PL_ci.repository

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@DataJpaTest
class CommitRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager

    @Autowired
    CommitRepository commitRepository

    @Test
    void whenFindById_thenReturnCommit(){
        //ToDo: add test
    }
}
