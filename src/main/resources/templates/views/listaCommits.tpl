yieldUnescaped '<!DOCTYPE html>'
html {
  head {
    title("Commits del alumno $alumno.nombre $alumno.apellido1 $alumno.apellido2")
    link(rel: 'stylesheet', href: '/webjars/bootstrap/css/bootstrap.min.css')
  }
   body {
    div(class: 'container') {

      h2("Commits del alumno $alumno.nombre $alumno.apellido1 $alumno.apellido2")
      table(class:'table table-dark table-striped'){
      	thead{
      		tr{
      			th('Id')
      			th('URL')
				th('Fecha')
				th('Errores')
				th('Correctos')
      		}
      	}
      	tbody{
      		commits.each { commit ->
	      		tr {
	      			td("$commit.commitId ")
	      			td{ a(href:"$commit.commitUrl ",target:'_blank','Jenkins')}
					td("$commit.commitFecha")
					td{ if (commit.commitNumErrores>0){
					        a(href:"commits/$commit.commitId","$commit.commitNumErrores")
					    }
					    else {"$commit.commitNumErrores"}
					}
					td("$commit.commitNumCorrectos")
	      		}
	      	}
      	}
      }
    }
  }
}
