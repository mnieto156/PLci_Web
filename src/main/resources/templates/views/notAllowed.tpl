layout 'layouts/alumnoLayout.tpl',true,
	pageTitle: "No permitido",
	mainBody: contents{
	def errorMsg = error ?: "No autorizado"
	    h3("Error: $errorMsg")
	    yield "Pulse " a(href:'/',"aquí") yield " para volver a la página principal"
	}