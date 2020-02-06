package es.uned.lsi.PL_ci.service

import es.uned.lsi.PL_ci.entity.restClient.GiteaUser
import reactor.core.publisher.Mono

interface GiteaService {

    Mono<GiteaUser> getUser(String username)
}