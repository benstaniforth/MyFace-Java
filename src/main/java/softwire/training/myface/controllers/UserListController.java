package softwire.training.myface.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softwire.training.myface.models.dbmodels.User;
import softwire.training.myface.models.viewmodels.AllUsersViewModel;
import softwire.training.myface.services.UsersService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/users")
public class UserListController {

    private final UsersService usersService;

    @Autowired
    public UserListController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(value = "")
    public ModelAndView getAllWalls(Principal principal) {

        List<User> allUsers = usersService.getAllUsers();

        AllUsersViewModel allUsersViewModel = new AllUsersViewModel();
        allUsersViewModel.loggedInUsername = principal.getName();
        allUsersViewModel.setAllUsers(allUsers);

        return new ModelAndView("users", "model", allUsersViewModel);
    }
}
