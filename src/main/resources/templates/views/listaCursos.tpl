layout 'layouts/adminLayout.tpl', true,
pageTitle: 'Cursos de Procesadores de Lenguaje',
mainBody: contents{
      table(id:'cursos', class:'table table-dark table-striped'){
      	thead{
      		tr{
      			th('Id')
      			th('Año')
				th('Asignatura')
                th('Alumnos')
				th('Proyecto base')
				th('Cerrado')
      		}
      	}
      	tbody{
      		cursos.each { curso ->
	      		tr {
	      			td("$curso.cursoId ")
	      			td("$curso.anio")
					td("$curso.asignatura")
                    td{ if (!curso.cursoAlumnos.isEmpty()){
                            def num = curso.cursoAlumnos.size()
                            a(href:"/alumnos/lista/$curso.nombre","$num Alumnos")
                        }
                        else{
                            yield('Sin Alumnos')
                        }
                    }
					td{
					    a(href:curso.baseRepository?:"http://my.plci.local/gitea", target:"_blank",'Gitea')
				    }
				    td{
				        if(!curso.cerrado){
				            a(href:"$curso.nombre/cerrarCurso"){
				                i(class:'fa fa-unlock')
				            }
				        }
				        else{
                            i(class:'fa fa-lock')
				        }
				    }
				}
	      	}
      	}
      }
        script(){
            yieldUnescaped ''' $(document).ready(function(){
                $('#cursos').DataTable( {
                    "language": { "url":"https://cdn.datatables.net/plug-ins/1.10.20/i18n/Spanish.json"},
                    "order": [[1,"desc"],[2,"desc"]],
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