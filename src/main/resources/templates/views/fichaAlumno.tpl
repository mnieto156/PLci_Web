def hiddenUser = alumno.user
layout 'layouts/main.tpl',true,
	pageTitle: "Alumno $alumno.nombre $alumno.apellido1 $alumno.apellido2",
	mainBody: contents{
	  form(action:'guardarAlumno', method:'POST'){
		div(class:'form-row'){
            div(class:'form-group col-md-6') {
                label(for:'username','Id')
                input(type:'text', class:'form-control', name:'username', placeholder:"$alumno.user.username", disabled:true, value:alumno.user.username)
                }
            div(class:'form-group col-md-6') {
                label(for:'nombre','Nombre')
                input(type:'text', class:'form-control', name:'nombre', placeholder:"$alumno.nombre", value:alumno.nombre?:'')
                }
            div(class:'form-group col-md-6') {
                label(for:'apellido1','Apellido 1')
                input(type:'text', class:'form-control', name:'apellido1', placeholder:"$alumno.apellido1", value:alumno.apellido1?:'')
                }
            div(class:'form-group col-md-6') {
                label(for:'apellido2','Apellido 2')
                input(type:'text', class:'form-control', name:'apellido2', placeholder:"$alumno.apellido2", value:alumno.apellido2?:'')
                }
            }
        div(class:'form-row'){
            div(class:'form-group col-md-6') {
                label(for:'correo','Correo')
                input(type:'email', class:'form-control', name:'correo', placeholder:"$alumno.correo", value:alumno.correo, disabled:true)
                }
            div(class:'form-group col-md-2') {
                label(for:'curso','Curso')
                input(type:'text', class:'form-control', name:'curso', placeholder:"$alumno.curso", value:alumno.curso, disabled:true)
                }
            div(class:'form-group col-md-6') {
                label(for:'repositorio','Repositorio')
                input(type:'text', class:'form-control', name:'repositorio', placeholder:"$alumno.repositorio", value:alumno.repositorio?:'', disabled:true)
                }
		}
		div(class:'form-row'){
            div(class:'form-group col-md-6') {
                label(for:'password','Contraseña')
                input(type:'password', class:'form-control', name:'newPassword', value:hiddenUser.password?:'changeme')
                }
            div(class:'form-group col-md-2') {
                label(for:'enviar','Guardar cambios')

                button(type:'submit', class:'btn btn-md btn-secondary btn-block', id:'enviar', title:'Guardar los cambios'){ yield "Guardar"}
                }
		}
        input(type:'hidden',name:'alumnoId',value:alumno.alumnoId)
        input(type:'hidden',name:'correo',value:alumno.correo)
        input(type:'hidden',name:'curso',value:alumno.curso)
        input(type:'hidden',name:'repositorio',value:alumno.repositorio)
        input(type:'hidden',name:'oldPass',value:hiddenUser.password)



        }
	  }
	}
