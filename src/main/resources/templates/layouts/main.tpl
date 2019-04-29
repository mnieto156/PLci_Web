yieldUnescaped '<!DOCTYPE html>'
html {
  head {
	title(pageTitle)
	link(rel: 'stylesheet', href: '/webjars/bootstrap/css/bootstrap.min.css')
  }
   body {
	div(class: 'jumbotron text-center'){
		h1('Sistema CI/CD de Procesadores de Lenguaje')
	}
	div(class: 'container') {
	  nav(class: 'navbar navbar-expand-sm bg-dark navbar-dark fixed-top') {
		a(class:'navbar-brand',href:'/','PLCI')
		button( class:"navbar-toggler", type:"button", "data-toggle":"collapse", "data-target":"#collapsibleNavbar"){
			span (class:"navbar-toggler-icon")
		}
		div(class:"collapse navbar-collapse", id:"collapsibleNavbar")
		ul(class: 'navbar-nav') {
			li(class: 'nav-item'){
				a(class:'nav-link',href:'/usuarios/lista','Alumnos')
			}
			li(class: 'nav-item') {
				a(class:'nav-link',href:'#','Ayuda')
			}
			
		}
	   
	  }
	  h2(pageTitle)
	  mainBody()
	}
  }
}