package es.uned.lsi.PL_ci.entity.restClient

import com.fasterxml.jackson.annotation.JsonProperty

class GiteaRepo {
    Integer id
    String name
    String description
    //String owner
    @JsonProperty("private")
    private Boolean _private

    void setPrivate(boolean p) {
        this._private = p
    }

    boolean getPrivate() {
        return this._private
    }
    Boolean archived
    Boolean template
    String html_url
}
