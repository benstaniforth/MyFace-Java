package softwire.training.myface.models.viewmodels;

import softwire.training.myface.models.dbmodels.Post;
import softwire.training.myface.models.dbmodels.Reaction;
import softwire.training.myface.models.dbmodels.User;

import java.util.List;

public class WallViewModel {

    public String loggedInUsername;
    public String wallOwnerUsername;
    public String wallOwnerFullname;
    public List<Post> posts;
    private List<User> allUsers;

    public List<User> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(List<User> allUsers) {
        this.allUsers = allUsers;
    }

}
