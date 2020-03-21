layout isAdmin ? 'layouts/adminLayout.tpl':'layouts/alumnoLayout.tpl',
    true,
    pageTitle: "Commits del alumno $alumno.nombre $alumno.apellido1 $alumno.apellido2",
    mainBody: contents{
        div(class:'table-responsive'){
            table(class:'table table-dark table-striped responsive', id:'commits-table'){
                thead{
                    tr{
                        th('Id')
                        th('Curso')
                        th('URL')
                        th('Fecha')
                        th('Errores')
                        th('Correctos')
                        th('Comentarios')
                    }
                }
                tbody{
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
                            td{
                                button( id:"verComents", class:"btn btn-md btn-secondary btn-block",
                                        type:"button", "data-toggle":"modal", "data-target":"#comentarios"){
                                    commit.comentarios.size()>0 ? i(class:"fa fa-comments", style:"color:red") : i(class:"fa fa-comments")
                                }
                            }
                        }
                    }
                }
            }
        }
        div(class:"col-md-4"){
            button(class:"btn btn-lg btn-secondary btn-block", onclick:"history.go(-1);", title:"Volver a la página anterior"){
                yield "Volver"
            }
        }
        div(class:"modal fade", id:"comentarios"){
            div(class:"modal-dialog modal-lg"){
                div(class:"modal-content"){

                    //Header
                    div(class:"modal-header"){
                        h4(class:"modal-title"){yield 'Comentarios'}
                        button( type:"button", class:"close", "data-dismiss":"modal"){yieldUnescaped '&times;'}
                    }
                    //Body
                    div(class:"modal-body"){
                        textarea(class:"form-control", id:"nuevoComentario", placeholder:"Nuevo Comentario (max. 450 caracteres)", maxlength:450){}
                        //tabla comentarios
                        table(id:'comentarios-table', class:"table table-dark table-striped responsive", style:"width:100%;table-layout:fixed;")
                    }
                    //Footer
                    div(class:"modal-footer"){
                        button( type:"button", class:"btn btn-lg btn-secondary btn-block", title:"Guardar comentario", id:"guardarComentario"){yield 'Guardar Comentario'}

                    }
                }
            }

        }
        script(){
            yieldUnescaped ''' $(document).ready(function(){
                var selectedId=0;
                var commitTable = $('#commits-table').DataTable( {
                    "language": { "url":"/webjars/datatables-plugins/i18n/Spanish.json"},
                    "order":[[3,"desc"]],
                    "columnDefs": [
                        {
                            "targets": [0],
                            "visible": false,
                            "searchable": false
                        },
                        {
                            "targets": [6],
                            "searchable": false,
                            "orderable": false
                        }
                    ]
                });

                var comentsTable = $('#comentarios-table').DataTable({
                    "language": { "url":"/webjars/datatables-plugins/i18n/Spanish.json"},
                    destroy: true,
                    "ajax":{
                        "url":'/comentarios/findByCommit/0',
                        "dataSrc": function(d){ return d}
                    },
                    "columns":[
                        {title:"ID",    data:"comentarioId", visible: false},
                        {title:"Texto", data:"contenido"},
                        {title:"Fecha", data:"comentFecha"},
                        {title:"Autor", data:"username", visible:false}
                    ],
                    "columnDefs":[
                        {
                            "targets":1,
                            "width":"auto"
                        },
                        {
                            "targets":2,
                            "width":"230px",
                            "render": function(data){
                                moment.locale('es');
                                return moment(data).format('llll');
                            }
                        }
                    ],
                    "order":[[0,"desc"]]
                });

                $('#commits-table tbody').on('click','#verComents', function(){
                    $('#nuevoComentario').val('');
                    let selected = commitTable.row($(this).parents('tr')).data();
                    selectedId = selected[0];
                    comentsTable.ajax.url('/comentarios/findByCommit/'+ selectedId).load();

                });

                $('#comentarios').on('shown.bs.modal', function(e){
                    $.fn.dataTable.tables( {visible: true, api: true} ).columns.adjust();
                });

                $('#guardarComentario').click(function(){
                    let comentContent = $('#nuevoComentario').val();
                    let data={
                        username: $('#menuUserName').text(),
                        commitId: selectedId,
                        contenido: comentContent
                    };
                    $.ajax({
                        url:'/comentarios/nuevo',
                        method:'POST',
                        data: data,
                        complete: function(){
                           setTimeout( function(){
                               comentsTable.ajax.reload(null,false);
                           },500);
                        },
                        success: function(d,t,x){
                            alert('Guardado');
                        },
                        error: function(x,s,e){
                            alert('Error al guardar comentario');
                        }
                    });
                });

            });'''
        }

    }