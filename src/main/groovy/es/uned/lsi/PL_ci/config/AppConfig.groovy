package es.uned.lsi.PL_ci.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@ConfigurationProperties(prefix = "app")
@Component
class AppConfig {
    private final Gitea gitea = new Gitea()
    static class Gitea {
        String token
        String baseurl
    }

    Gitea getGitea() {
        gitea
    }
}
