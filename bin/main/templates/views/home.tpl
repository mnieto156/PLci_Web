layout 'layouts/main.tpl',true,
	pageTitle: 'Página Principal de Sistema CI/CD de Procesadores de Lenguaje',
	mainBody: contents{
      div("This is an application using Boot $bootVersion and Groovy templates $groovyVersion")
      div("Usuario: $userName")
}