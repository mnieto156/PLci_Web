layout 'layouts/main.tpl',true,
	userNavbar: contents{
        nav(class: 'navbar navbar-expand-sm bg-dark navbar-dark fixed-top') {
            a(class:'navbar-brand',href:'/','Inicio')
            button( class:"navbar-toggler", type:"button", "data-toggle":"collapse", "data-target":"#collapsibleNavbar"){
                span (class:"navbar-toggler-icon")
            }
            div(class:"collapse navbar-collapse", id:"collapsibleNavbar"){
                ul(class: 'navbar-nav mr-auto mt-2 mt-lg-0') {
                    li(class: 'nav-item'){
                        a(class:'nav-link',href:'/alumnos/lista','Alumnos')
                    }
                    li(class: 'nav-item') {
                        a(class:'nav-link',href:'#','Ayuda')
                    }

                }
                ul(class:"navbar-nav my-2 my-lg-0"){
                    def usuId = userName ?: 'Usuario'
                    li(class:"dropdown"){
                        a(class:"btn btn-secondary dropdown-toggle", href:'#', role:"button", "data-toggle":"dropdown"){
                            yield "$usuId"
                        }
                        ul(class:"dropdown-menu"){
                            //a(class:"dropdown-item", href:"/alumnos/$usuId/ficha", 'Ficha')
                            a(class:"dropdown-item", href:"/alumnos/$usuId/notas", 'Notas')
                            li(class:"dropdown-divider"){}
                            a(class:"dropdown-item", href:"/logout", 'Salir')
                        }
                    }
                }
            }

        }
	}