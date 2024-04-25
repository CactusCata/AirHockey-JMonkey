package fr.utln.jmonkey.bot.behaviors;

import com.jme3.math.Vector3f;
import fr.utln.jmonkey.bot.Movement;
import fr.utln.jmonkey.bot.MovementType;
import fr.utln.jmonkey.items.list.Puck;
import fr.utln.jmonkey.items.list.Racket;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BotBehaviorUpdater {

    private static final Set<BotBehavior> behaviors = new HashSet<>();
    private BotBehavior lastBehavior = null;
    private List<Movement> nextMoves = null;
    private int moveIndex = 0;

    static {
        behaviors.add(new BotBehaviorBackAndAttack());
        behaviors.add(new BotBehaviorDefense());
        behaviors.add(new BotBehaviorAttack());
        behaviors.add(new BotBehaviorWaiting());
    }

    public void update(Puck puck, Racket myRacket, Racket enemyRacket) {
        if (nextMoves != null && moveIndex == nextMoves.size()) {
            nextMoves = null;
        }

        if (nextMoves == null) {
            for (BotBehavior botBehavior : behaviors) {
                if (botBehavior.canStartBehavior(puck, myRacket, enemyRacket)) {
                    lastBehavior = botBehavior;
                    break;
                }
            }

            moveIndex = 0;
            this.nextMoves = lastBehavior.getNextMoves(puck, myRacket, enemyRacket);
        }

        Movement nextMove = nextMoves.get(moveIndex++);
        Vector3f velocity = BotBehavior.fromBehaviorSpaceToTableSideSpace(nextMove.getMovement());
        if (nextMove.getMovementType() == MovementType.RELATIVE) {
            myRacket.getControl().setLinearVelocity(velocity);
        } else {
            Vector3f currentBotPos = myRacket.getControl().getPhysicsLocation();
            myRacket.getControl().setLinearVelocity(velocity.subtract(currentBotPos));
        }

    }

}
