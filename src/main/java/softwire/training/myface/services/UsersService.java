package softwire.training.myface.services;

import org.jdbi.v3.core.Handle;
import org.springframework.stereotype.Service;
import softwire.training.myface.models.dbmodels.Users;

import java.util.List;

@Service
public class UsersService extends DatabaseService {

    public List<Users> getAllUsers() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT fullname FROM users")
                        .mapToBean(Users.class)
                        .list()
        );
    }


    public void addNewUser(Users user) {
        jdbi.useHandle(handle ->
                handle.createUpdate("INSERT INTO users (username, password, fullname) " +
                        "VALUES (:username, :password, :fullname)")
                        .bind("username", user.getUsername())
                        .bind("password", user.getPassword())
                        .bind("fullname", user.getFullname())
                        .execute()
        );
    }

    public void editUser(Users user) {
        jdbi.useHandle(handle ->
                handle.createUpdate("UPDATE users SET username = :username, password = :password, fullname = :fullname")
                        .bind("username", user.getUsername())
                        .bind("password", user.getPassword())
                        .bind("fullname", user.getFullname())
                        .execute()
        );
    }

}
