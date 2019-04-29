layout 'layouts/main.tpl',
	pageTitle: "Alumno $alumno.nombre $alumno.apellido1 $alumno.apellido2",
	mainBody: contents{
	  table(class:'table table-dark table-striped'){
		thead{
            tr{
                th('Id')
                th('Nombre')
                th('Correo')
                th('Perfil')
                th('Commits')
            }
		}
        tbody{

            tr {
                td("$alumno.usuarioId ")
                td("$alumno.nombre $alumno.apellido1 $alumno.apellido2")
                td("$alumno.correo")
                td("$alumno.perfil.perfilDescripcion")
                td{
                    a(href:"$alumno.usuarioId/commits", 'Commits')
                }
            }
        }

	  }
	}
  }
}
