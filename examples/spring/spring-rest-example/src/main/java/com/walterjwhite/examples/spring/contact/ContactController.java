package com.walterjwhite.examples.spring.contact;

import com.walterjwhite.google.resilience4j.annotation.RateLimited;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.job.parameters.InvalidJobParametersException;
import org.springframework.batch.core.launch.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.launch.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.launch.JobRestartException;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contact")
@RequiredArgsConstructor
@Slf4j
public class ContactController {
  private final ContactService contactService;

  @RateLimited
  @GetMapping
  public String contactForm(Model model) {
    model.addAttribute("contactForm", new ContactForm());
    return "contact";
  }

  @RateLimited
  @PostMapping
  public String submitContact(
      @ModelAttribute("contactForm") @Valid ContactForm form,
      BindingResult bindingResult,
      Model model) {
    if (bindingResult.hasErrors()) {
      return "contact";
    }

    try {
      contactService.send(form);
      model.addAttribute("successMessage", "Thank you — your message has been sent.");
      model.addAttribute("contactForm", new ContactForm());
      return "contact";
    } catch (IllegalArgumentException
        | IllegalStateException
        | MailException
        | JobInstanceAlreadyCompleteException
        | InvalidJobParametersException
        | JobExecutionAlreadyRunningException
        | JobRestartException e) {
      LOGGER.error("error while sending message", e);
      model.addAttribute("errorMessage", "Error occurred while sending your message.");
      model.addAttribute("contactForm", form);
      return "contact";
    }
  }
}
