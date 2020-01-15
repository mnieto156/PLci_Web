layout 'layouts/adminLayout.tpl', true,
pageTitle: 'Alumnos de Procesadores de Lenguaje',
mainBody: contents{
      table(class:'table table-dark table-striped'){
      	thead{
      		tr{
      			th('Id')
      			th('Nombre')
				th('Correo')
                th('Curso')
				th('Ficha')
				th('Commits')
      		}
      	}
      	tbody{
      		alumnos.sort{it.alumnoId}.each { alumno ->
	      		tr {
	      			td("$alumno.alumnoId ")
	      			td("$alumno.nombre $alumno.apellido1 $alumno.apellido2")
					td("$alumno.correo")
                    td{ if (alumno.cursosAlumno){
                            ul{
                                alumno.cursosAlumno.curso.each { curso ->
                                    li{
                                        a(href:"/alumnos/lista/$curso.nombre","$curso.nombre")
                                    }

                                }
                            }
                        }
                        else{
                            a(href:"/alumnos/lista/$alumno.curso","$alumno.curso")
                        }
                    }
					td{
					    if (alumno.user){
						    a(href:"/alumnos/$alumno.user.username/ficha",'Ficha')
						}
				    }
				    td{
				        a(href:"/alumnos/$alumno.user.username/avances",'Ver avances')
				    }
				}
	      	}
      	}
      }
    }