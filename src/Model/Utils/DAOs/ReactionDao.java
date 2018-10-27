package Model.Utils.DAOs;

import Model.Reaction;

import java.util.List;

public interface ReactionDao {
    List<Reaction> getAllReactions();
    void createReaction(String Reaction_name, int risk, String description);
}
