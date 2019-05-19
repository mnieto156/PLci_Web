layout 'layouts/main.tpl',true,
	pageTitle: "Alumno $alumno.nombre $alumno.apellido1 $alumno.apellido2",
	mainBody: contents{
	  form(action:'guardarAlumno', method:'POST'){
		div(class:'form-row'){
            div(class:'form-group col-md-6') {
                label(for:'inputId','Id')
                input(type:'text', class:'form-control', id:'inputId', placeholder:"$alumno.user.username", disabled)
                }
            div(class:'form-group col-md-6') {
                label(for:'inputNombre','Nombre')
                input(type:'text', class:'form-control', id:'inputId', placeholder:"$alumno.nombre")
                }
            div(class:'form-group col-md-6') {
                label(for:'inputAp1','Apellido 1')
                input(type:'text', class:'form-control', id:'inputId', placeholder:"$alumno.apellido1")
                }
            div(class:'form-group col-md-6') {
                label(for:'inputId','Id')
                input(type:'text', class:'form-control', id:'inputId', placeholder:"$alumno.apellido2)
                }
            }
                th('Nombre')
                th('Correo')
                th('Curso')
                th('Repositorio')
                th('Commits')
            }
		}
        tbody{

            tr {
                td("$alumno. ")
                td("$alumno.nombre $alumno.apellido1 $alumno.apellido2")
                td("$alumno.correo")
                td("$alumno.curso")
                td{a(href:"$alumno.repositorio","Gitea")}
                td{a(href:"$alumno.alumnoId/commits", 'Commits')}
            }
        }

	  }
	}
