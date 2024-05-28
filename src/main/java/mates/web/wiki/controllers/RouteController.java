package mates.web.wiki.controllers;

import mates.web.wiki.services.MailService;
import org.springframework.scheduling.annotation.Async;
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

    private final ContactRepository contactRepository;
    private final MailService mailService;

    RouteController(ContactRepository contactRepository, MailService mailService) {
        this.contactRepository = contactRepository;
        this.mailService = mailService;
    }

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

        sendConfirmationEmails(contact);
        contactRepository.save(contact);
        return new ModelAndView("redirect:/grupo-1-1-wiki/thanks");
    }

    @GetMapping("/thanks")
    public ModelAndView getFormThanks() {
        return new ModelAndView("thanks");
    }

    @Async
    public void sendConfirmationEmails(Contact contact) {
        mailService.sendEmail(
                contact.getEmail(),
                "Thank you for contacting us - TwoMates",
                getContentToUser(contact),
                true
        );
        mailService.sendEmail(
                "naingmd@proton.me",
                "An user has sent a message - TwoMates",
                getContentToCrew(contact),
                true
        );
        mailService.sendEmail(
                "nicolasmonta1807@gmail.com",
                "An user has sent a message - TwoMates",
                getContentToCrew(contact),
                true
        );
    }

    private static String getContentToUser(Contact contact) {
        String contentToUser = "Dear [[name]],<br>"
                + "Thank you for contacting us. Here's a copy of your message<br>"
                + "<p>[[message]]</p>"
                + "Thank you.<br>"
                + "We will contact you back soon.<br>"
                + "TwoMates";

        contentToUser = contentToUser.replace("[[name]]", contact.getName() + " " + contact.getLastName());
        contentToUser = contentToUser.replace("[[message]]", contact.getMessage());
        return contentToUser;
    }

    private static String getContentToCrew(Contact contact) {
        String contentToCrew = "An user has sent a message <br>"
                + "<p>[[username]]: </p>"
                + "<p>[[message]]</p>"
                + "<br><br><br>"
                + "<p>Sent from: [[userEmail]]</p>";
        contentToCrew = contentToCrew.replace("[[username]]", contact.getName() + " " + contact.getLastName());
        contentToCrew = contentToCrew.replace("[[message]]", contact.getMessage());
        contentToCrew = contentToCrew.replace("[[userEmail]]", contact.getEmail());
        return contentToCrew;
    }

}
