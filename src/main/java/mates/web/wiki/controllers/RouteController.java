package mates.web.wiki.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import mates.web.wiki.model.Contact;
import mates.web.wiki.repositories.ContactRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/grupo-1-1-wiki")
public class RouteController {

  @Autowired
  private ContactRepository contactRepository;

  @GetMapping(value = {"/", ""})
  public ModelAndView getHomePage() {
    return new ModelAndView("home");
  }

  @GetMapping("/contact")
  public ModelAndView getContactPage() {
    ModelAndView modelAndView = new ModelAndView("contact");
    modelAndView.addObject("contact", new Contact());
    return modelAndView;
  }

  @GetMapping("/project")
  public ModelAndView getProjectPage() {
    return new ModelAndView("project");
  }

  @PostMapping("/form/contact")
  public ModelAndView postContactForm(@Valid Contact contact, BindingResult bindingResult, ModelAndView modelAndView) {
    if (bindingResult.hasErrors()) {
      modelAndView.setViewName("contact");
      modelAndView.addObject("contact", contact);
      return modelAndView;
    }

    contactRepository.save(contact);
    return new ModelAndView("home");
  }

}
