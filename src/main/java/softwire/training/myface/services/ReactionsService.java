package softwire.training.myface.services;

import org.springframework.stereotype.Service;
import softwire.training.myface.models.dbmodels.Reaction;

import java.util.List;

@Service
public class ReactionsService extends DatabaseService {

    List<Reaction> getAllReactions(String recipientUsername, String reaction) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT id, post_id, username, type FROM reactions WHERE username = :recipientUsername, type = :reaction")
                        .bind("recipientUsername", recipientUsername)
                        .bind("reaction", reaction)
                        .mapToBean(Reaction.class)
                        .list()
        );
    }

    public void addReaction(Reaction reaction) {

        jdbi.withHandle(handle ->
                handle.createUpdate("INSERT INTO reactions (post_id, username, type) VALUES (:postId, :userName, :type)")
                        .bind("postId", reaction.getPostId())
                        .bind("username", reaction.getUserName())
                        .bind("type", reaction.getType())
                        .execute()

        );
    }


    public void deleteReaction(int id) {

        jdbi.withHandle(handle ->
                handle.createUpdate("DELETE FROM reactions WHERE id = :id")
                        .bind("id", id)
                        .execute()

        );
    }



}


