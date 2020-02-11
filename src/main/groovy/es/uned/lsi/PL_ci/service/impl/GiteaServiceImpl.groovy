package es.uned.lsi.PL_ci.service.impl

import es.uned.lsi.PL_ci.config.AppConfig
import es.uned.lsi.PL_ci.entity.restClient.GiteaRepo
import es.uned.lsi.PL_ci.entity.restClient.GiteaUser
import es.uned.lsi.PL_ci.service.GiteaService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class GiteaServiceImpl implements GiteaService {
    private static final Logger logger = LoggerFactory.getLogger(GiteaServiceImpl.class)
    private final WebClient webClient

    @Autowired
    GiteaServiceImpl(AppConfig appConfig) {
        this.webClient = WebClient.builder()
                .baseUrl(appConfig.getGitea().baseurl + "/api/v1")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
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
    Mono<GiteaRepo> getRepoOfUser(String username, String reponame) {
        webClient.get()
                .uri("/repos/${username}/${reponame}")
                .retrieve()
                .bodyToMono(GiteaRepo)
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
                logger.info sb.toString()
            }
            Mono.just(clientRequest)
        })
    }
}
