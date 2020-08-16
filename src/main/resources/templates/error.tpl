layout 'layouts/main.tpl',true,
	pageTitle: 'Error',
	mainBody: contents{
		div("Se ha producido un error")

		a(href:"/"){ yield "Volver a la página principal"}
    }