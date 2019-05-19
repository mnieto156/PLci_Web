layout 'layouts/main.tpl',true,
	pageTitle: "No permitido",
	mainBody: contents{
	def errorMsg = error ?: "No autorizado"
	    h3("No tiene permiso para ver esta página: $errorMsg")
	    yield "Pulse " a(href:'/',"aquí") yield " para volver a la página principal"
	}