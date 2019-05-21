layout 'layouts/main.tpl',true,
	pageTitle: 'Error',
	mainBody: contents{
      div("Se ha producido un error: ")
      if (errors != null){
          errors.each{error ->
           ul("$error.code - $error.defaultMessage)
           }
      }
    }