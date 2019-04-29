yieldUnescaped '<!DOCTYPE html>'
html {
  head {
    title('Alumnos de Procesadores de Lenguaje')
    link(rel: 'stylesheet', href: '/webjars/bootstrap/css/bootstrap.min.css')
  }
   body {
    div(class: 'container') {
      div(class: 'navbar') {
        div(class: 'form-group') {
        
        }
       
      }
      h2('Alumnos de Procesadores de Lenguaje')
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
      		alumnos.each { alumno ->
	      		tr {
	      			td("$alumno.usuarioId ")
	      			td("$alumno.nombre $alumno.apellido1 $alumno.apellido2")
					td("$alumno.correo")
					td("$alumno.perfil.perfilDescripcion")
					td{
						a(href:"$alumno.usuarioId",'Ficha')
				    }
				}
	      	}
      	}
      }
    }
  }
}
        