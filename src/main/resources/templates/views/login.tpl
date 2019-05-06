layout 'layouts/main.tpl',
pageTitle: '',
mainBody: contents{
    div(class:'row justify-content-center'){
        div(class:'col-md-8'){
            div(class:'card'){
                div(class:'card-header'){yield "Acceso"}
                div(class:'card-body'){
                    form(action:"login",method:"POST"){
                        div(class:"form-group row"){
                            input(name:'username', id:'username', type:'text', class:'form-control',placeholder:'Usuario')
                            input(name:'password', id:'password', type:'password', class:'form-control',placeholder:'Contraseña')

                        }
                        div(class:"col-md-4"){
                            button(class:"btn btn-lg btn-secondary btn-block", type:'submit', title: 'Acceder a la página'){
                                            yield "Acceder"
                                        }
                            input(type:'hidden',name='${_csrf.parameterName}',value='${_csrf.token}')
                        }

                }
            }
        }
    }

    }
}