layout 'layouts/main.tpl',
pageTitle: 'Acceder a la página de Procesadores de Lenguaje',
mainBody: contents{
    form(class:'form-signin'){
        div(class:"form-group"){
            input(name:'usuarioemail', type:'email', class:'form-control',placeholder:'email')
            input(name:'usuariopwd', type:'password', class:'form-control',placeholder:'contraseña')
            button(class:"btn btn-lg btn-primary btn-block", type:'submit', title: 'Acceder a la página'){
                yield " Acceder "
            }
            input(type:'hidden',name='${_csrf.parameterName}',value='${_csrf.token}')
        }
    }
}