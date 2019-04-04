package softwire.training.myface.services;

import org.springframework.stereotype.Service;
import softwire.training.myface.models.dbmodels.Reactions;

import java.util.List;

@Service
public class ReactionsService extends DatabaseService {

    List<Reactions> getAllReactions(String recipientUsername, String reaction) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT id, post_id, username, type FROM reactions WHERE username = :recipientUsername, type = :reaction")
                        .bind("recipientUsername", recipientUsername)
                        .bind("reaction", reaction)
                        .mapToBean(Reactions.class)
                        .list()
        );

    }
}


