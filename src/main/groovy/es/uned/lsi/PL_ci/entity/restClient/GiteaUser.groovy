package es.uned.lsi.PL_ci.entity.restClient

class GiteaUser {
    Integer id
    String username
    String email
    String full_name
    String password
    Boolean is_admin
    Boolean active
    Boolean allow_create_organization
    Integer max_repo_creation
}