yieldUnescaped '<!DOCTYPE html>'
html {
  head {
    title("Commit del $commit.commitFecha del alumno $alumno.nombre $alumno.apellido1 $alumno.apellido2")
    link(rel: 'stylesheet', href: '/webjars/bootstrap/css/bootstrap.min.css')
  }
   body {
    div(class: 'container') {

      h2("Commit del $commit.commitFecha del alumno $alumno.nombre $alumno.apellido1 $alumno.apellido2")
      table(class:'table table-dark table-striped'){
      	thead{
      		tr{
      			th('Id')
      			th('URL')
				th('Paso')
      		}
      	}
      	tbody{
      		commit.commitErrors.each { commitError ->
	      		tr {
	      			td("$commitError.errorId ")
	      			td{ a(href:"http://my.plci.local/jenkins$commitError.errorUrl/log ",target:'_blank','Jenkins log')}
					td("$commitError.errorStageName")
	      		}
	      	}
      	}
      }
    }
  }
}
