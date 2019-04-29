package es.uned.lsi.PL_ci.controller

import org.springframework.boot.Banner
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class HomeController {
	@RequestMapping("/")
	def home() {
		new ModelAndView(
			"views/home",
			[bootVersion: Banner.package.implementationVersion, 
         groovyVersion: GroovySystem.version])
	}
}
