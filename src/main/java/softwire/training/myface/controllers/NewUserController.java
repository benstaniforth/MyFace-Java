package softwire.training.myface.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import softwire.training.myface.services.UsersService;

@Controller
@RequestMapping(value = "/signup")
public class NewUserController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView showSignUpPage() {

        return new ModelAndView("signup");
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public RedirectView signUp(
            @ModelAttribute("username") String username,
            @ModelAttribute("password") String password,
            @ModelAttribute("fullName") String fullName
    ) {

        // TODO: Implement sign-up!
        UsersService.
        return new RedirectView("/users");
    }


}
