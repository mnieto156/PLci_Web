layout isAdmin ? 'layouts/adminLayout.tpl':'layouts/alumnoLayout.tpl',true,
	pageTitle: "Alumno $alumno.nombre $alumno.apellido1 $alumno.apellido2",
	mainBody: contents{
        table(class:'table table-dark table-striped'){
            thead{
                tr{
                    th('Id')
                    th('Nombre')
                    th('Correo')
                    th('Curso')
                    th('Repositorio')
                }
            }
            tbody{
                tr {
                    td("$alumno.alumnoId ")
                    td("$alumno.nombre $alumno.apellido1 $alumno.apellido2")
                    td("$alumno.correo")
                    td{
                        ul{
                            alumno.cursosAlumno.curso.each { curso ->
                                li{
                                    a(href:"$curso.nombre/commits","$curso.nombre")
                                }
                            }
                        }
                    }
                    td{
                        ul{
                            alumno.cursosAlumno.each { cursoAlumno ->
                                li{
                                    a(href:"$cursoAlumno.repositorio",target:'_blank',"Gitea")
                                }
                            }
                        }
                    }
                }
            }
        }
        div(class:"col-md-4"){
            button(class:"btn btn-lg btn-secondary btn-block", onclick:"history.go(-1);", title:"Volver a la página anterior"){
                yield "Volver"
            }
        }
	}
