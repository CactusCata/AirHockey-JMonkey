package fr.utln.jmonkey.bot;

import com.jme3.math.Vector3f;
import fr.utln.jmonkey.items.list.Puck;
import fr.utln.jmonkey.items.list.Racket;

import java.util.List;

public class BotBehaviorDefense implements BotBehavior {

    @Override
    public boolean canStartBehavior(Puck puck, Racket botRacket, Racket otherRacket) {
        Vector3f puckLocation = puck.getControl().getPhysicsLocation();
        puckLocation = BotBehavior.fromTableSideSpaceToBehaviorSpace(puckLocation);
        return puckLocation.z < 0.0f;
    }

    @Override
    public List<Vector3f> getNextMoves(Puck puck, Racket botRacket, Racket otherRacket) {
        return null;
    }
}