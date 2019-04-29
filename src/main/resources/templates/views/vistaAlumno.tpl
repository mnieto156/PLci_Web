yieldUnescaped '<!DOCTYPE html>'
html {
  head {
	title("Alumno $alumno.nombre $alumno.apellido1 $alumno.apellido2")
	link(rel: 'stylesheet', href: '/webjars/bootstrap/css/bootstrap.min.css')
  }
   body {
	div(class: 'container') {

	  h2("Alumno $alumno.nombre $alumno.apellido1 $alumno.apellido2")
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
