package es.uned.lsi.PL_ci.service.impl

import es.uned.lsi.PL_ci.config.AppConfig
import es.uned.lsi.PL_ci.restClient.GiteaRepo
import es.uned.lsi.PL_ci.restClient.GiteaUser
import es.uned.lsi.PL_ci.service.GiteaService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class GiteaServiceImpl implements GiteaService {
    private static final Logger logger = LoggerFactory.getLogger(GiteaServiceImpl)
    private final WebClient webClient

    @Autowired
    GiteaServiceImpl(AppConfig appConfig) {
        this.webClient = WebClient.builder()
                .baseUrl("${appConfig.getGitea().baseurl}/api/v1")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "token ${appConfig.getGitea().token}")
                .filter(logRequest())
                .build()
    }

    @Override
    Mono<GiteaUser> getUser(String username) {
        webClient.get()
                .uri("/users/${username}")
                .retrieve()
                .bodyToMono(GiteaUser)
    }

    @Override
    Mono<GiteaUser> addUser(GiteaUser user) {
        def uri = webClient.post().uri("/admin/users")
        def inserter = BodyInserters.fromValue(user)
        uri.body(inserter).retrieve().bodyToMono(GiteaUser)
    }

    @Override
    Mono<GiteaUser> updateUser(GiteaUser user) {
        def uri = webClient.patch().uri("/admin/users/${user.username}")
        def inserter = BodyInserters.fromValue(user)
        uri.body(inserter).retrieve().bodyToMono(GiteaUser)
    }

    @Override
    Mono<GiteaRepo> getRepoOfUser(String username, String reponame) {
        webClient.get()
                .uri("/repos/${username}/${reponame}")
                .retrieve()
                .bodyToMono(GiteaRepo)
    }

    @Override
    Mono<List<GiteaRepo>> getAllReposOfUser(String username) {
        webClient.get()
                .uri("/users/${username}/repos")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<GiteaRepo>>() {})
    }

    @Override
    Mono<GiteaRepo> addRepo(GiteaRepo repo, String userAdmin) {
        def uri = webClient.post().uri("/admin/users/${userAdmin}/repos")
        def inserter = BodyInserters.fromValue(repo)
        uri.body(inserter).retrieve().bodyToMono(GiteaRepo)
    }

    @Override
    Mono<GiteaRepo> updateRepo(GiteaRepo repo, String userAdmin) {
        def uri = webClient.patch().uri("/repos/${userAdmin}/${repo.name}")
        def inserter = BodyInserters.fromValue(repo)
        uri.body(inserter).retrieve().bodyToMono(GiteaRepo)
    }

    @Override
    Mono<GiteaRepo> addCollaboratorToRepo(String repoName, String userAdmin, String userCollab) {
        def uri = webClient.put().uri("/repos/${userAdmin}/${repoName}/${userCollab}")
        def map = new LinkedMultiValueMap()
        map.add('permission','write')
        def inserter = BodyInserters.fromMultipartData(map)
        uri.body(inserter).retrieve().bodyToMono(GiteaRepo)
    }

    private ExchangeFilterFunction logRequest() {
        ExchangeFilterFunction.ofRequestProcessor({ clientRequest ->
            if (logger.isDebugEnabled()) {
                StringBuilder sb = new StringBuilder("Request: \n")
                clientRequest
                    .headers()
                    .forEach({ name, values ->
                        values.forEach({ value ->
                            sb.append("${name}=${value}")
                        })
                    })
                logger.debug sb.toString()
            }
            Mono.just(clientRequest)
        })
    }
}
