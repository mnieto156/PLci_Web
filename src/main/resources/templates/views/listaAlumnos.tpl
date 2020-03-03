layout 'layouts/adminLayout.tpl', true,
pageTitle: 'Alumnos de Procesadores de Lenguaje',
mainBody: contents{
      table(id:'alumnos', class:'table table-dark table-striped'){
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
      		alumnos.each { alumno ->
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
                            yield('Sin Cursos')
                        }
                    }
					td{
					    if (alumno.user){
						    a(href:"/alumnos/$alumno.user.username/ficha",'Ficha')
						}
				    }
				    td{ if (alumno.cursosAlumno){
				        a(href:"/alumnos/$alumno.user.username/avances",'Ver avances')}
				    }
				}
	      	}
      	}
      }
        script(){
            yieldUnescaped ''' $(document).ready(function(){
                $('#alumnos').DataTable( {
                    "language": { "url":"https://cdn.datatables.net/plug-ins/1.10.20/i18n/Spanish.json"},
                    "columnDefs": [
                        {
                            "targets": [0],
                            "visible": false,
                            "searchable": false
                        }
                    ]
                });
            });'''
        }
    }