package simpleauthorizationserver.backend.authserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {

    @GetMapping("/**")
    public String page() {
        return "/client/index.html";
    }
}
