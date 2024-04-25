package fr.utln.jmonkey.bot.behaviors;

import com.jme3.math.Vector3f;
import fr.utln.jmonkey.bot.Movement;
import fr.utln.jmonkey.bot.MovementType;
import fr.utln.jmonkey.items.list.Puck;
import fr.utln.jmonkey.items.list.Racket;

import java.util.ArrayList;
import java.util.List;

public class BotBehaviorBackAndAttack implements BotBehavior {
    @Override
    public boolean canStartBehavior(Puck puck, Racket botRacket, Racket otherRacket) {
        Vector3f puckLocation = puck.getControl().getPhysicsLocation();
        puckLocation = BotBehavior.fromTableSideSpaceToBehaviorSpace(puckLocation);
        return puckLocation.z < 0.25f && puckLocation.z > 0.0f;
    }

    @Override
    public List<Movement> getNextMoves(Puck puck, Racket botRacket, Racket otherRacket) {
        List<Movement> moves = new ArrayList<>();
        Vector3f puckVelo = puck.getControl().getLinearVelocity();
        Vector3f puckPos = puck.getControl().getPhysicsLocation();

        Vector3f goal = puckPos;
        goal.add(puckVelo);
        goal = goal.subtract(botRacket.getControl().getPhysicsLocation());

        goal = BotBehavior.fromTableSideSpaceToBehaviorSpace(goal);

        moves.add(Movement.of(MovementType.RELATIVE, goal.x, -goal.z));
        moves.add(Movement.ZERO);
        moves.add(Movement.ZERO);
        moves.add(Movement.ZERO);
        moves.add(Movement.ZERO);
        moves.add(Movement.of(MovementType.RELATIVE, goal.x, 2 * goal.z));

        return moves;
    }
}
