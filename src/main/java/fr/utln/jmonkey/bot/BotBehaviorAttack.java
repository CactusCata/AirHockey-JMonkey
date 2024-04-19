package fr.utln.jmonkey.bot;

import com.jme3.math.Vector3f;
import fr.utln.jmonkey.items.list.Puck;
import fr.utln.jmonkey.items.list.Racket;

import java.util.ArrayList;
import java.util.List;

public class BotBehaviorAttack implements BotBehavior {

    @Override
    public boolean canStartBehavior(Puck puck, Racket botRacket, Racket otherRacket) {
        Vector3f puckLocation = puck.getControl().getPhysicsLocation();
        puckLocation = BotBehavior.fromTableSideSpaceToBehaviorSpace(puckLocation);
        return puckLocation.z < 0.75f && puckLocation.z > 0.25f;
    }

    @Override
    public List<Vector3f> getNextMoves(Puck puck, Racket botRacket, Racket otherRacket) {
        List<Vector3f> moves = new ArrayList<>();

        return moves;
    }
}
