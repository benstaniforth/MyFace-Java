package softwire.training.myface.models.viewmodels;

import softwire.training.myface.models.dbmodels.Post;
import softwire.training.myface.models.dbmodels.Users;

import java.util.List;

public class WallViewModel {

    public String loggedInUsername;
    public String wallOwnerUsername;
    public List<Post> posts;

    private List<Users> allUsers;

    public List<Users> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(List<Users> allUsers) {
        this.allUsers = allUsers;
    }
}
