package es.uned.lsi.PL_ci.entity.restClient

class GiteaRepo {
    Integer id
    String name
    String description
    //String owner
    private Boolean _private

    void setPrivate(boolean p) {
        this._private = p
    }

    boolean getPrivate() {
        return this._private
    }
    Boolean archived
}
