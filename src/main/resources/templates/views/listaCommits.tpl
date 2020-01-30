layout isAdmin ? 'layouts/adminLayout.tpl':'layouts/alumnoLayout.tpl',
    true,
	pageTitle: "Commits del alumno $alumno.nombre $alumno.apellido1 $alumno.apellido2",
	mainBody: contents{
      table(class:'table table-dark table-striped', id:'commits'){
      	thead{
      		tr{
      			th('Id')
      			th('Curso')
      			th('URL')
				th('Fecha')
				th('Errores')
				th('Correctos')
      		}
      	}
      	tbody{
      		/*commits.sort{it.commitFecha}
      		commits.reverse(true)*/
      		commits.each { commit ->
	      		tr {
	      			td("$commit.commitId ")
	      			td{ if (commit.cursoAlumno){
	      			        a(href:"/alumnos/$alumno.user.username/$commit.cursoAlumno.curso.nombre/commits","$commit.cursoAlumno.curso.nombre")
	      			    }
	      			    else{"Sin curso"}
	      			}
	      			td{ a(href:"$commit.commitUrl ",target:'_blank','Jenkins')}
					td("$commit.commitFecha")
					td{ if (commit.commitNumErrores>0){
					        a(href:"/alumnos/$alumno.user.username/commits/$commit.commitId","$commit.commitNumErrores")
					    }
					    else {"$commit.commitNumErrores"}
					}
					td("$commit.commitNumCorrectos")
	      		}
	      	}
      	}
      }
      script(){
          yieldUnescaped ''' $(document).ready(function(){
              $('#commits').DataTable( {
                  "language": { "url":"https://cdn.datatables.net/plug-ins/1.10.20/i18n/Spanish.json"},
                  "order":[[3,"desc"]]
              });
          });'''
      }

    }