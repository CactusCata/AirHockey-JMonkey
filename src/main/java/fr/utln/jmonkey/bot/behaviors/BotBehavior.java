package fr.utln.jmonkey.bot.behaviors;

import com.jme3.math.Vector3f;
import fr.utln.jmonkey.bot.Movement;
import fr.utln.jmonkey.items.list.Puck;
import fr.utln.jmonkey.items.list.Racket;

import java.util.List;

public interface BotBehavior {

    float MIN_X_BOT_TABLE = -40;
    float MAX_X_BOT_TABLE = 40;
    float MIN_Z_BOT_TABLE = 0;
    float MAX_Z_BOT_TABLE = 84;


    boolean canStartBehavior(Puck puck, Racket botRacket, Racket otherRacket);

    List<Movement> getNextMoves(Puck puck, Racket botRacket, Racket otherRacket);

    static Vector3f fromTableSideSpaceToBehaviorSpace(Vector3f tableSideSpace) {
        float behaviorSpaceX = (tableSideSpace.x - MIN_X_BOT_TABLE) / (MAX_X_BOT_TABLE - MIN_X_BOT_TABLE);
        float behaviorSpaceZ = (tableSideSpace.z - MIN_Z_BOT_TABLE) / (MAX_Z_BOT_TABLE - MIN_Z_BOT_TABLE);
        return new Vector3f(behaviorSpaceX, 0.0f, behaviorSpaceZ);
    }

    static Vector3f fromBehaviorSpaceToTableSideSpace(Vector3f behaviorSpace) {
        float tableSideSpaceX = behaviorSpace.x * ((MAX_X_BOT_TABLE - MIN_X_BOT_TABLE)) + MIN_X_BOT_TABLE;
        float tableSideSpaceZ = behaviorSpace.z * ((MAX_Z_BOT_TABLE - MIN_Z_BOT_TABLE)) + MIN_Z_BOT_TABLE;
        return new Vector3f(tableSideSpaceX, 0.0f, tableSideSpaceZ);
    }

}
