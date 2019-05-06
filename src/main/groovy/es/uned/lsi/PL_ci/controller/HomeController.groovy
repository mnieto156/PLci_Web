package es.uned.lsi.PL_ci.controller

import org.springframework.boot.Banner
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
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

	@RequestMapping(value = "/login",method = RequestMethod.GET)
	def login(Model model,String error, String logout) {
		if (error != null)
			model.addAttribute("errorMsg", "Your username and password are invalid.")

		if (logout != null)
			model.addAttribute("msg", "You have been logged out successfully.")

		return "views/login"
	}
}
