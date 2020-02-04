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
                        def date = new Date().getYear()+1900
                        def listaAnios = date-5..date+10 //set as minYear..maxYear
                        for(def anio: listaAnios){
                            option(value:"${anio}-${anio+1}","${anio}-${anio+1}")
                        }
                    }
                }
                div(class:'form-group col-md-2') {
                    select(id:'cursoAsignatura', name:'cursoAsignatura', class:'custom-select'){
                        option('PL1')
                        option('PL2')
                    }
                }
                div(class:'form-group col-md-2'){
                    button(type:'submit', class:'btn btn-md btn-secondary btn-block', id:'addCurso', title:'Añadir curso'){
                        yield 'Añadir curso '
                        i(class:'fa fa-plus-square'){}
                    }
                }
                div(class:"form-group col-md-2"){
                    button(class:"btn btn-md btn-secondary btn-block", onclick:"history.go(-1);", title:"Volver a la página anterior"){
                        yield "Cancelar"
                    }
                }
            }
	    }
	    script(){
            yieldUnescaped ''' $(document).ready(function(){
                var currentYear = (new Date).getFullYear();
                var nextYear = currentYear+1;
                var cursoActual = currentYear +'-'+nextYear;
                $("#cursoAnio option").filter(function(){
                    return $(this).text()==cursoActual;}).prop("selected",true);
            }); '''
        }
	}