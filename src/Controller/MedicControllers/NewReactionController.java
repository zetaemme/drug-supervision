package Controller.MedicControllers;

import Model.Utils.DaoImpl.ReactionDaoImpl;

public class NewReactionController {
    public ReactionDaoImpl reactionDao;

    // Adds a new reaction with the corresponding data
    public String addNewReaction(int risk, String description) {
        reactionDao = new ReactionDaoImpl();

        String Reaction_name = risk + description.substring(0, 5).toUpperCase().replace(" ", "");

        reactionDao.createReaction(Reaction_name, risk, description);

        return Reaction_name;
    }
}
