package softwire.training.myface.services;

import org.springframework.stereotype.Service;
import softwire.training.myface.models.dbmodels.User;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService extends DatabaseService {

    public List<User> getAllUsers() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM users")
                        .mapToBean(User.class)
                        .list()
        );
    }


    public void addNewUser(User user) {
        jdbi.useHandle(handle ->
                handle.createUpdate("INSERT INTO users (username, password, fullname) " +
                        "VALUES (:username, :password, :fullname)")
                        .bind("username", user.getUsername())
                        .bind("password", user.getPassword())
                        .bind("fullname", user.getFullName())
                        .execute()
        );
    }

    public void editUser(User user) {
        jdbi.useHandle(handle ->
                handle.createUpdate("UPDATE users SET username = :username, password = :password, fullname = :fullname")
                        .bind("username", user.getUsername())
                        .bind("password", user.getPassword())
                        .bind("fullname", user.getFullName())
                        .execute()
        );
    }

    public Optional <String> getPassword(String username) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT password FROM users WHERE username = :username")
                        .bind("username", username)
                        .mapTo(String.class)
                        .findFirst()
        );
    }
    public Optional <String> getFullname(String username) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT fullname FROM users WHERE username = :username")
                .bind("username", username)
                .mapTo(String.class)
                .findFirst()
        );
    }

}
