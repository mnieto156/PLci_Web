package es.uned.lsi.PL_ci.controller

import es.uned.lsi.PL_ci.restClient.GiteaRepo
import es.uned.lsi.PL_ci.restClient.GiteaUser
import es.uned.lsi.PL_ci.service.GiteaService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.client.WebClientResponseException
import reactor.core.publisher.Mono

@RestController
@RequestMapping("giteaRESTClient")
class GiteaController {
    @Autowired
    private GiteaService giteaService

    private static final Logger logger = LoggerFactory.getLogger(GiteaController)


    @GetMapping("/{username}")
    Mono<GiteaUser> getUser(@PathVariable String username) {
        giteaService.getUser(username)
    }

    @GetMapping("/{username}/{reponame}")
    Mono<GiteaRepo> getRepoOfUser(@PathVariable String username, @PathVariable String reponame) {
        giteaService.getRepoOfUser(username, reponame)
    }

    @GetMapping("/{username}/allrepos")
    Mono<List<GiteaRepo>> getAllReposOfUser(@PathVariable String username){
        giteaService.getAllReposOfUser(username)
    }
    @PostMapping("/{newUser}")
    Mono<GiteaUser> addNewUser(@PathVariable String newUser){
        giteaService.addUser new GiteaUser(
                username: newUser,
                email: "${newUser}@alumno.uned.es",
                password: 'changeMe.1234')
    }

    @ExceptionHandler(WebClientResponseException)
    ResponseEntity<String> handleWebClientResponseException(WebClientResponseException ex) {
        logger.error "Error from WebClient - Status ${ex.getRawStatusCode()}, Body ${ex.getResponseBodyAsString()}", ex
        ResponseEntity.status(ex.getRawStatusCode()).body ex.getResponseBodyAsString()
    }
}
