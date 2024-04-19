package fr.utln.jmonkey.bot;

import com.jme3.math.Vector3f;
import fr.utln.jmonkey.items.list.Puck;
import fr.utln.jmonkey.items.list.Racket;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BotBehaviorUpdater {

    private static final Set<BotBehavior> behaviors = new HashSet<>();
    private BotBehavior lastBehavior = null;
    private List<Vector3f> nextMoves = null;
    private int moveIndex = 0;

    static {
        behaviors.add(new BotBehaviorBackAndAttack());
        behaviors.add(new BotBehaviorDefense());
        behaviors.add(new BotBehaviorAttack());
    }

    public void updateBehavior(Puck puck, Racket myRacket, Racket enemyRacket) {
        if (nextMoves != null && moveIndex == nextMoves.size()) {
            nextMoves = null;
        }

        if (nextMoves == null) {
            for (BotBehavior botBehavior : behaviors) {
                if (botBehavior.canStartBehavior(puck, myRacket, enemyRacket)) {
                    lastBehavior = botBehavior;
                }
            }

            moveIndex = 0;
            this.nextMoves = lastBehavior.getNextMoves(puck, myRacket, enemyRacket);
        }

        myRacket.getControl().setLinearVelocity(nextMoves.get(moveIndex++));

    }

}
