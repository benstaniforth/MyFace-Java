package softwire.training.myface.services;

import org.jdbi.v3.core.mapper.reflect.BeanMapper;
import org.springframework.stereotype.Service;
import softwire.training.myface.models.dbmodels.Post;
import softwire.training.myface.models.dbmodels.Reaction;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostsService extends DatabaseService {

    public List<Post> getPostsOnWall(String recipient) {
        return jdbi.withHandle(handle ->
                new ArrayList<>(handle.createQuery(
                        "SELECT posts.id AS p_id, posts.sender AS p_sender, posts.recipient AS p_recipient, posts.content AS p_content, " +
                                "reactions.id AS r_id, reactions.post_id AS r_post_id, reactions.username AS r_username, reactions.type AS r_type " +
                                "FROM posts " +
                                "LEFT JOIN reactions " +
                                "ON posts.id = reactions.post_id " +
                                "WHERE recipient = :recipient")
                        .bind("recipient", recipient)
                        .registerRowMapper(BeanMapper.factory(Post.class, "p"))
                        .registerRowMapper(BeanMapper.factory(Reaction.class, "r"))
                        .reduceRows(new LinkedHashMap<Integer, Post>(), (map, rowView) -> {
                            Integer currentPostId = rowView.getColumn("p_id", Integer.class);
                            Post currentPost = map.computeIfAbsent(currentPostId, id -> rowView.getRow(Post.class));
                            if (rowView.getColumn("r_id", Integer.class) != null) {
                                currentPost.addReaction(rowView.getRow(Reaction.class));
                            }
                            return map;
                        })
                        .values()
                )
        );
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
                handle.createQuery("SELECT posts.id AS p_id, posts.sender AS p_sender, posts.recipient AS p_recipient, posts.content AS p_content, " +
                        "reactions.id AS r_id, reactions.post_id AS r_post_id, reactions.username AS r_username, reactions.type AS r_type " +
                        "FROM posts " +
                        "LEFT JOIN reactions " +
                        "ON posts.id = reactions.post_id " +
                        "WHERE posts.id = :id")
                        .bind("id", id)
                        .registerRowMapper(BeanMapper.factory(Post.class, "p"))
                        .registerRowMapper(BeanMapper.factory(Reaction.class, "r"))
                        .reduceRows(Optional.<Post>empty(), (postO, rowView) ->
                                Optional.of(postO.orElseGet(() -> rowView.getRow(Post.class)))
                                        .map(post -> {
                                            if (rowView.getColumn("r_id", Integer.class) != null) {
                                                post.addReaction(rowView.getRow(Reaction.class));
                                            }
                                            return post;
                                        })
                        )
                        .get()
        );
    }
}
