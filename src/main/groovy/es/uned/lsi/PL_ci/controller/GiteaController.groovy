package es.uned.lsi.PL_ci.controller

import es.uned.lsi.PL_ci.entity.restClient.GiteaUser
import es.uned.lsi.PL_ci.service.GiteaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class GiteaController {
    @Autowired
    private GiteaService giteaService

    @GetMapping("/gitea/{username}")
    Mono<GiteaUser> getUser(@PathVariable String username) {
        giteaService.getUser(username)
    }
}
