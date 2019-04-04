package softwire.training.myface.services;

import org.jdbi.v3.core.Handle;
import org.springframework.stereotype.Service;
import softwire.training.myface.models.dbmodels.Reactions;

import java.util.List;

@Service
public class ReactionsService extends DatabaseService {

    List<Reactions> getAllReacionsWave(String recipientUsername, ){
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT id, post_id, username, wave FROM reactions WHERE username = :recipientUsername, wave = 1")
                    .bind("recipientUsername", recipientUsername)

        }
    }

}
