package es.uned.lsi.PL_ci.controller

import es.uned.lsi.PL_ci.entity.User
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class HomeController {

    @RequestMapping("/")
    def home(@AuthenticationPrincipal User user) {

        if (user.authorities.any { it.authority == 'ROLE_ADMIN' }) {
            new ModelAndView(
                    "views/homeAdmin",
                    [userName: user.username])
        } else {
            new ModelAndView(
                    "views/home",
                    [userName: user.username])

        }
    }

    @RequestMapping(value = "/login")
    def login(Model model, String logout, String error) {

        if (logout != null)
            model.addAttribute("logout", true)
        if (error != null)
            model.addAttribute("loginerror", true)
        if (model != null)
            println model.toString()

        return "views/login"
    }


    @RequestMapping(value = "/notallowed")
    def notAllowed(Model model, String error, @AuthenticationPrincipal User user) {

        model.addAttribute("error", error ?: "No tiene permiso")
        model.addAttribute("userName", user.username)
        return 'views/notAllowed'
    }
}
