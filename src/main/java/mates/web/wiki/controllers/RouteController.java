package mates.web.wiki.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RouteController {

  @GetMapping("/")
  public ModelAndView getHomePage() {
    return new ModelAndView("pages/home");
  }

  @GetMapping("/contact")
  public ModelAndView getContactPage() {
    return new ModelAndView("pages/contact");
  }

  @GetMapping("/project")
  public ModelAndView getProjectPage() {
    return new ModelAndView("pages/project");
  }

}
