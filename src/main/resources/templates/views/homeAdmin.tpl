layout 'layouts/adminLayout.tpl',true,
	pageTitle: 'Página principal',
	mainBody: contents{

        div(class:'card', style:'width: 24rem;'){
            h3(class:'card-header'){ yield "Bienvenido: $userName"}
            div(class:'card-body' ){

                p(class:'card-text'){ yield 'Es usted administrador de esta aplicación'}
                div(class:'btn-group', role:'group'){
                    button(type:'button', class: "btn btn-secondary ", title:'Cambiar contraseña', "data-toggle":"modal", "data-target":"#changepass"){
                        yield 'Cambiar contraseña'
                    }
                    button(type:'button', class: "btn btn-secondary ", title:'Nuevo Administrador', "data-toggle":"modal", "data-target":"#newadmin"){
                        yield 'Nuevo administrador'
                    }
                }
            }

        }

        div(class:"modal fade", id:"newadmin"){
            div(class:"modal-dialog"){
                div(class:"modal-content"){

                    //Header
                    div(class:"modal-header"){
                        h4(class:"modal-title"){yield 'Nuevo administrador'}
                        button( type:"button", class:"close", "data-dismiss":"modal"){yieldUnescaped '&times;'}
                    }
                    //Body
                    div(class:"modal-body"){
                        div(class:'form-group col-md-6') {
                            label(for:'username','Nombre de usuario')
                            div(class:"input-group"){
                                div(class:'input-group-prepend'){span(class:'input-group-text'){i(class:'fa fa-user'){}}}
                                input(name:'username', id:'username', type:'text', class:'form-control',placeholder:'user')
                            }
                        }
                        div(class:'form-group col-md-6') {
                            label(for:'password','Contraseña')
                            div(class:"input-group"){
                                div(class:'input-group-prepend'){span(class:'input-group-text'){i(class:'fa fa-key'){}}}
                                input(name:'password', id:'password', type:'password', class:'form-control',placeholder:'Contraseña')
                            }
                        }
                    }
                    //Footer
                    div(class:"modal-footer"){
                        button( type:"button", class:"btn btn-lg btn-secondary btn-block", title:"Guardar cambios", id:"guardaradmin"){yield 'Guardar'}

                    }
                }
            }
        }
        div(class:"modal fade", id:"changepass"){
            div(class:"modal-dialog"){
                div(class:"modal-content"){

                    //Header
                    div(class:"modal-header"){
                        h4(class:"modal-title"){yield 'Cambiar contraseña'}
                        button( type:"button", class:"close", "data-dismiss":"modal"){yieldUnescaped '&times;'}
                    }
                    //Body
                    div(class:"modal-body"){
                        div(class:'form-group col-md-12') {
                            label(for:'newpassword','Nueva Contraseña')

                            div(class:"input-group"){
                                div(class:'input-group-prepend'){span(class:'input-group-text'){i(class:'fa fa-key'){}}}
                                input(name:'newpassword', id:'newpassword', type:'password', class:'form-control',placeholder:'Contraseña')
                            }
                        }
                    }
                    //Footer
                    div(class:"modal-footer"){
                        button( type:"button", class:"btn btn-lg btn-secondary btn-block", title:"Guardar cambios", id:"guardarpass"){yield 'Guardar Cambios'}

                    }
                }
            }
        }
        script(){
            yieldUnescaped '''
                $(document).ready(function(){
                    $("#guardarpass").click(function(){
                        const newpass = $("#newpassword").val();
                        $.ajax({
                            url:'/users/'+$('#menuUserName').text()+'/password',
                            method:'PUT',
                            dataType: 'json',
                            contentType: 'application/json',
                            data:  newpass ,
                            success: function(d,t,x){
                                alert('Guardado');
                            },
                            error: function(x,s,e){
                                alert('Error al guardar la nueva contraseña');
                            }
                        });
                    });
                    $("#guardaradmin").click(function(){
                        const data = {
                            username : $("#username").val(),
                            password : $("#password").val(),
                            authorities: [{ authority: 'ROLE_ADMIN'}]
                        };
                        $.post({
                            url: '/users/',
                            data: JSON.stringify(data),
                            dataType: 'json',
                            contentType: 'application/json',
                            success: function(){
                                alert('Guardado');
                            },
                            error: function(){
                                alert('Error al guardar un nuevo administrador');
                            }
                        });

                    });
                });
            '''
        }
	}