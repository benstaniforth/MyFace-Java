package softwire.training.myface.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import softwire.training.myface.models.dbmodels.Post;
import softwire.training.myface.models.dbmodels.Reaction;
import softwire.training.myface.models.viewmodels.WallViewModel;
import softwire.training.myface.services.PostsService;
import softwire.training.myface.services.ReactionsService;
import softwire.training.myface.services.UsersService;

import java.security.Principal;

@Controller
@RequestMapping("/wall")
public class WallController {

    private final PostsService postsService;
    private final UsersService usersService;
    private final ReactionsService reactionsService;

    @Autowired
    public WallController(PostsService postsService, UsersService usersService, ReactionsService reactionsService) {
        this.postsService = postsService;
        this.usersService = usersService;
        this.reactionsService = reactionsService;
    }

    @RequestMapping(value = "/{wallOwnerUsername}", method = RequestMethod.GET)
    public ModelAndView getWall(
            @PathVariable("wallOwnerUsername") String wallOwnerUsername,
            Principal principal
    ) {

        WallViewModel wallViewModel = new WallViewModel();
        wallViewModel.loggedInUsername = principal.getName();
        wallViewModel.wallOwnerUsername = wallOwnerUsername;
        wallViewModel.wallOwnerFullname = usersService.getFullname(wallOwnerUsername).get();
        wallViewModel.posts = postsService.getPostsOnWall(wallOwnerUsername);

        return new ModelAndView("wall", "model", wallViewModel);
    }

    @RequestMapping(value = "/{wallOwnerUsername}/post", method = RequestMethod.POST)
    public RedirectView postOnWall(
            @PathVariable("wallOwnerUsername") String wallOwnerUsername,
            @ModelAttribute("content") String content,
            Principal loggedInUserPrincipal
    ) {

        String senderUsername = loggedInUserPrincipal.getName();
        postsService.createPost(senderUsername, wallOwnerUsername, content);

        return new RedirectView("/wall/" + wallOwnerUsername);
    }

    @RequestMapping(value = "/{wallOwnerUsername}/delete/{id}", method = RequestMethod.POST)
    public RedirectView deletePost(
            @PathVariable("wallOwnerUsername") String wallOwnerUsername,
            @PathVariable("id") int id,
            Principal loggedInUserPrincipal
    ) {
        Post singlePost = postsService.getSinglePost(id);
        String recipient = singlePost.getRecipient();
        String sender = singlePost.getSender();
        if (loggedInUserPrincipal.getName().equalsIgnoreCase(recipient) || loggedInUserPrincipal.getName().equalsIgnoreCase(sender)) {
            postsService.deletePost(id);
        } else {
            throw new AccessDeniedException("Only the sender and the recipient can delete posts!");
        }
        return new RedirectView("/wall/" + wallOwnerUsername);
    }

    @RequestMapping(value = "/{wallOwnerUsername}/post/{id}/reaction/{type}", method = RequestMethod.POST)
    public RedirectView reactOnPost(
            @PathVariable("wallOwnerUsername") String wallOwnerUsername,
            @PathVariable("id") int id,
            @PathVariable("type") String type,
            @ModelAttribute("content") String content,
            Principal loggedInUserPrincipal
    ) {
        Reaction reaction = new Reaction();
        reaction.setUserName(loggedInUserPrincipal.getName());
        reaction.setPostId(id);
        reaction.setType(type);
        reactionsService.addReaction(reaction);

        return new RedirectView("/wall/" + wallOwnerUsername);
    }

}
