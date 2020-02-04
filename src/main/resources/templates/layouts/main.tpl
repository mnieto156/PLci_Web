yieldUnescaped '<!DOCTYPE html>'
html {
    head {
        meta(name:'viewport', content:'width=device-width, initial-scale=1, shrink-to-fit=no')
        title(pageTitle)
        link(rel: 'stylesheet', href: '/webjars/bootstrap/css/bootstrap.min.css')
        link(rel: 'stylesheet', href: 'https://cdn.datatables.net/v/bs4/dt-1.10.20/r-2.2.3/datatables.min.css')
        link(rel: 'stylesheet', href: '/webjars/font-awesome/css/font-awesome.min.css')
    }
    script(src:'/webjars/jquery/jquery.min.js'){}
    script(src:'/webjars/popper.js/umd/popper.min.js'){}
    script(src:'/webjars/bootstrap/js/bootstrap.min.js'){}
    script(src:'https://cdn.datatables.net/v/bs4/dt-1.10.20/r-2.2.3/datatables.min.js'){}
    body {
        div(class: 'jumbotron text-center'){
            h1('Sistema de pruebas automatizadas de Procesadores de Lenguaje')
        }
        div(class: 'container') {
            if(userNavbar){
                userNavbar()
            }
            else {
                nav(class: 'navbar navbar-expand-sm bg-dark navbar-dark fixed-top') {
                    a(class:'navbar-brand',href:'/','Inicio')
                    button( class:"navbar-toggler", type:"button", "data-toggle":"collapse", "data-target":"#collapsibleNavbar"){
                        span (class:"navbar-toggler-icon")
                    }
                    div(class:"collapse navbar-collapse", id:"collapsibleNavbar"){
                        ul(class: 'navbar-nav mr-auto mt-2 mt-lg-0') {
                            li(class: 'nav-item') {
                                a(class:'nav-link',href:'#','Ayuda')
                            }
                        }
                    }
                }
            }
            h2(pageTitle)
            mainBody()
        }
    }
}