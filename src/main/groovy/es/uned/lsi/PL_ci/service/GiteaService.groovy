package es.uned.lsi.PL_ci.service

import es.uned.lsi.PL_ci.restClient.GiteaRepo
import es.uned.lsi.PL_ci.restClient.GiteaUser
import reactor.core.publisher.Mono

interface GiteaService {

    Mono<GiteaUser> getUser(String username)

    Mono<GiteaUser> addUser(GiteaUser user)

    Mono<GiteaUser> updateUser(GiteaUser user)

    Mono<GiteaRepo> getRepoOfUser(String username, String reponame)

    Mono<List<GiteaRepo>> getAllReposOfUser(String username)

    Mono<GiteaRepo> addRepo(GiteaRepo repo, String userAdmin)

    Mono<GiteaRepo> updateRepo(GiteaRepo repo, String userAdmin)

    Mono<GiteaRepo> addCollaboratorToRepo(String repoName, String userAdmin, String userCollab)
}