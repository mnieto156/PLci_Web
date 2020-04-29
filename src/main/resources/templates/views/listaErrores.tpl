layout isAdmin ? 'layouts/adminLayout.tpl':'layouts/alumnoLayout.tpl',true,
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
	      			td{ a(href:"http://jenkins.plci.local/$commitError.errorUrl/log ",target:'_blank','Jenkins log')}
					td("$commitError.errorStageName")
	      		}
	      	}
      	}
      }
      div(class:"col-md-4"){
          button(class:"btn btn-lg btn-secondary btn-block", onclick:"history.go(-1);", title:"Volver a la página anterior"){
              yield "Volver"
          }
      }
    }

