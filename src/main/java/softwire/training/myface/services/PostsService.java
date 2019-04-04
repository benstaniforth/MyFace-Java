package softwire.training.myface.services;

import javafx.geometry.Pos;
import org.jdbi.v3.core.Handle;
import org.springframework.stereotype.Service;
import softwire.training.myface.models.dbmodels.Post;

import java.util.List;
import java.util.Optional;

@Service
public class PostsService extends DatabaseService {

    public List<Post> getPostsOnWall(String recipient) {
        try (Handle handle = jdbi.open()) {
            return handle
                    .createQuery("SELECT * FROM posts WHERE recipient = :recipient")
                    .bind("recipient", recipient)
                    .mapToBean(Post.class)
                    .list();
        }
    }

    public void createPost(String sender, String recipient, String content) {
        jdbi.withHandle(handle ->
                handle.createUpdate("INSERT INTO posts (sender, recipient, content) VALUES (:sender, :recipient, :content)")
                        .bind("sender", sender)
                        .bind("recipient", recipient)
                        .bind("content", content)
                        .execute()
        );
    }
    public void deletePost(int id) {
        jdbi.withHandle(handle ->
                handle.createUpdate("DELETE FROM posts WHERE id = :id")
                .bind("id", id)
                .execute()
                );
    }
    public Post getSinglePost(int id) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM posts WHERE id = :id")
                .bind("id", id)
                .mapToBean(Post.class)
                .findOnly()
        );
    }
}
