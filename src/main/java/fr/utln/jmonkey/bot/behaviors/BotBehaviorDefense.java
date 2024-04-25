package fr.utln.jmonkey.bot.behaviors;

import com.jme3.math.Vector3f;
import fr.utln.jmonkey.bot.Movement;
import fr.utln.jmonkey.bot.MovementType;
import fr.utln.jmonkey.items.list.Puck;
import fr.utln.jmonkey.items.list.Racket;

import java.util.ArrayList;
import java.util.List;

public class BotBehaviorDefense implements BotBehavior {

    @Override
    public boolean canStartBehavior(Puck puck, Racket botRacket, Racket otherRacket) {
        Vector3f puckLocation = puck.getControl().getPhysicsLocation();
        puckLocation = BotBehavior.fromTableSideSpaceToBehaviorSpace(puckLocation);
        return puckLocation.z < 0.0f;
    }

    @Override
    public List<Movement> getNextMoves(Puck puck, Racket botRacket, Racket otherRacket) {
        List<Movement> moves = new ArrayList<>();
        moves.add(Movement.of(MovementType.REAL, 0.5f, 0.8f));
        return moves;
    }
}
