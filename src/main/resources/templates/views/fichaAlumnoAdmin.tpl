layout 'layouts/adminLayout.tpl',true,
	pageTitle: contents{
        if (alumno?.nombre){
            yield "Alumno $alumno.nombre $alumno.apellido1 $alumno.apellido2"
        }
	    else { yield "Alumno nuevo" }
	},
	mainBody: contents{
        form(action:'guardarAlumnoAdmin', method:'POST'){
            div(class:'form-row'){
                div(class:'form-group col-md-6') {
                    label(for:'username','Id')
                    input(type:'text', class:'form-control', name:'username', placeholder:"userId123", value:alumno?.user?.username?:'')
                    }
                div(class:'form-group col-md-6') {
                    label(for:'nombre','Nombre')
                    input(type:'text', class:'form-control', name:'nombre', placeholder:"Nombre", value:alumno?.nombre?:'')
                    }
                div(class:'form-group col-md-6') {
                    label(for:'apellido1','Apellido 1')
                    input(type:'text', class:'form-control', name:'apellido1', placeholder:"Apellido1", value:alumno?.apellido1?:'')
                    }
                div(class:'form-group col-md-6') {
                    label(for:'apellido2','Apellido 2')
                    input(type:'text', class:'form-control', name:'apellido2', placeholder:"Apellido2", value:alumno?.apellido2?:'')
                    }
            }
            div(class:'form-row'){
                div(class:'form-group col-md-6') {
                    label(for:'correo','Correo')
                    input(type:'email', class:'form-control', name:'correo', placeholder:"userid123@alumno.uned.es", value:alumno?.correo?:'')
                    }
            }
            div(class:'form-row'){
                div(class:'form-group col-md-2') {
                    button(type:'submit', class:'btn btn-md btn-secondary btn-block', id:'enviar', title:'Guardar los cambios'){ yield "Guardar"}
                }
                div(class:"form-group col-md-2"){
                    button(type:'button', class:"btn btn-md btn-secondary btn-block", onclick:"history.go(-1);", title:"Volver a la página anterior"){
                        yield "Volver"
                    }
                }
            }
            input(type:'hidden',name:'alumnoId',value:alumno?.alumnoId)

            if(alumno?.nombre){
                table(id:'cursosAlumno', class:'table table-dark table-striped'){
                    caption('Cursos del alumno')
                    thead{
                        tr{
                            th('Curso')
                            th('Repositorio')
                        }
                    }
                    tbody{
                        if (alumno?.cursosAlumno){
                            alumno.cursosAlumno.each{cursoAlumno->
                                tr{
                                    td("$cursoAlumno.curso.nombre")
                                    td("$cursoAlumno.repositorio")
                                }
                            }
                        }
                    }
                }
                div(class:'form-row'){
                    div(class:'form-group col-md-4'){
                        select(id:'cursoAdd', name:'cursoAdd', class:'custom-select'){
                            option(selected:true, value:'', 'Elige curso')
                            cursos.each { curso->
                                if (!curso.cerrado){
                                    option(value:curso.nombre, curso.nombre)
                                }
                            }
                        }
                    }
                    div(class:'form-group col-md-2'){
                        button(type:'submit', class:'btn btn-md btn-secondary btn-block', id:'addCurso', title:'Añadir curso'){
                            yield 'Añadir a curso '
                            //i(class:'fa fa-plus-square')
                        }
                    }
                }
            }
        }
    }