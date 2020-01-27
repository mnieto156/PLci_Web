layout 'layouts/adminLayout.tpl',true,
	pageTitle: contents{
        if (curso?.nombre){
         yield "Curso $curso.nombre "
        }
	    else { yield "Curso nuevo" }
	},
	mainBody: contents{
	    form(action:'guardarCurso', method:'POST'){
	        div(class:'form-row'){
                div(class:'form-group col-md-6') {
                    select(id:'cursoAnio', name:'cursoAnio', class:'custom-select'){
                        def listaAnios = 1998..2035 //set as minYear..maxYear
                        def date = new Date()
                        for(def anio: listaAnios){
                            option(selected:anio=date[Calendar.YEAR],value:"${anio}-${anio+1}","${anio}-${anio+1}")
                        }
                    }

                }
                div(class:'form-group col-md-2') {
                    select(id:'cursoAsignatura', name:'cursoAsignatura', class:'custom-select'{
                        option('PL1')
                        option('PL2')
                    }
                }
                div(class:'form-group col-md-4'){
                    button(type:'submit', class:'btn btn-md btn-secondary btn-block', id:'addCurso', title:'Añadir curso'){
                        yield 'Añadir curso '
                        i(class:'fa fa-plus-square')
                    }
                }
            }
	    }
	}