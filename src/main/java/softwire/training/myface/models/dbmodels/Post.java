package softwire.training.myface.models.dbmodels;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Post {

    private int id;
    private String sender;
    private String recipient;
    private String content;
    private List<Reaction> allReactions = new ArrayList<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Reaction> getAllReactions() {
        return allReactions;
    }

    public void setAllReactions(List<Reaction> allReactions) {
        this.allReactions = allReactions;
    }

    public void addReaction(Reaction reaction) {
        this.allReactions.add(reaction);
    }

    public int getLikesCount() {
        int count = 0;
        for (Reaction reaction : allReactions) if (reaction.getType().equals("like")) count++;
        return count;
    }

    public int getWaveCount() {
        int count = 0;
        for (Reaction reaction : allReactions) if (reaction.getType().equals("wave")) count++;
        return count;
    }
    public int getFrownCount() {
        int count = 0;
        for (Reaction reaction : allReactions) if (reaction.getType().equals("frown")) count++;
        return count;
    }
}


