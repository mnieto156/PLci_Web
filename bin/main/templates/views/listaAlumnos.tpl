layout 'layouts/main.tpl',
pageTitle: 'Alumnos de Procesadores de Lenguaje',
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