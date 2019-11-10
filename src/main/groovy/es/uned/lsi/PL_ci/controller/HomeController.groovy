package es.uned.lsi.PL_ci.controller

import es.uned.lsi.PL_ci.entity.User
import org.springframework.boot.Banner
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView

@Controller
class HomeController {

    @RequestMapping("/")
    def home(@AuthenticationPrincipal User user) {
        if (user.authorities.any { it.authority == 'ROLE_ADMIN' }) {
            new ModelAndView(
                    "views/homeAdmin",
                    [userName: user.username])
        }
        else {
            new ModelAndView(
                    "views/home",
                    [userName: user.username])

        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    def login(Model model, String error, String logout, @AuthenticationPrincipal User user) {
        if (error )
            model.addAttribute("error", "Error al acceder a la página")

        if (user )
            model.addAttribute("userName", user.username)

        if (logout )
            model.addAttribute("msg", "Ha abandonado la sesión.")

        return "views/login"
    }
    @RequestMapping(value = "/notallowed")
    def notAllowed(Model model, String error, @AuthenticationPrincipal User user){
        if(error )
            model.addAttribute("error", error)
        model.addAttribute("userName",user.username)
        return 'views/notAllowed'
    }
}
