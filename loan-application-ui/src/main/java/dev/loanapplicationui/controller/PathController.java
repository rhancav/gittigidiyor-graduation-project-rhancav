package dev.loanapplicationui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PathController {
    @RequestMapping( method = {RequestMethod.GET}, path = {"/**","/"} )
    public String forwardAngularPaths() {
        return "redirect:/application-inquiry";
    }
}
