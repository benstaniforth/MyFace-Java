package softwire.training.myface.models.viewmodels;

import softwire.training.myface.models.dbmodels.User;

import java.util.List;

public class AllUsersViewModel {

    public String loggedInUsername;
    public List<String> allUsernames;

    private List<User> allUsers;

    public List<User> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(List<User> allUsers) {
        this.allUsers = allUsers;
    }
}
