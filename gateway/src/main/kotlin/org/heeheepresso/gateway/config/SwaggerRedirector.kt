package org.heeheepresso.gateway.config

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.view.RedirectView

@Controller
class SwaggerRedirector {

    @RequestMapping("/swagger")
    fun swagger(): RedirectView{
        return RedirectView("/swagger-ui/index.html")
    }
}