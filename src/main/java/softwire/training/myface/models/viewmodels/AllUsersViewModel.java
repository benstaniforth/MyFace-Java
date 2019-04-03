package softwire.training.myface.models.viewmodels;

import softwire.training.myface.models.dbmodels.Users;

import java.util.List;

public class AllUsersViewModel {

    public String loggedInUsername;
    public List<String> allUsernames;

    private List<Users> allUsers;

    public List<Users> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(List<Users> allUsers) {
        this.allUsers = allUsers;
    }
}
