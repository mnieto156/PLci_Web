layout 'layouts/main.tpl',true,
	pageTitle: "Commit del $commit.commitFecha del alumno $alumno.nombre $alumno.apellido1 $alumno.apellido2",
	mainBody: contents{
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

