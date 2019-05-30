layout 'layouts/adminLayout.tpl',true,
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
                th('Commits')
            }
		}
        tbody{

            tr {
                td("$alumno.alumnoId ")
                td("$alumno.nombre $alumno.apellido1 $alumno.apellido2")
                td("$alumno.correo")
                td("$alumno.curso")
                td{a(href:"$alumno.repositorio","Gitea")}
                td{a(href:"commits", 'Commits')}
            }
        }

	  }
	}
