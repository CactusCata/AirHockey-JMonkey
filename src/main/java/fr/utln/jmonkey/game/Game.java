package fr.utln.jmonkey.game;

import com.jme3.math.Vector3f;
import fr.utln.jmonkey.hud.HudScore;
import fr.utln.jmonkey.items.GoalResult;
import fr.utln.jmonkey.utils.MathUtils;
import lombok.Getter;

public class Game {

    public static Game instance;

    @Getter
    private final Player p1;
    @Getter
    private final Player p2;

    public Game(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        instance = this;
    }

    public void onPointWon(GoalResult goalResult) {
        if (goalResult == GoalResult.I_SCORED_GOAL) {
            p1.getScore().addPoint();
        } else if (goalResult == GoalResult.RIVAL_SCORED_GOAL) {
            p2.getScore().addPoint();
        }
        HudScore.onScoreUpdated();
    }

    public void correctFuturePos(PlayerNumber playerNumber, Vector3f futurePuckPos) {
        if (playerNumber == PlayerNumber.PLAYER_ONE) {
            futurePuckPos.setX(MathUtils.clamp(-45.0f, 45.0f, futurePuckPos.x));
            futurePuckPos.setZ(MathUtils.clamp(-84.0f, -15.0f, futurePuckPos.z));
        } else {
            futurePuckPos.setX(MathUtils.clamp(-45.0f, 45.0f, futurePuckPos.x));
            futurePuckPos.setZ(MathUtils.clamp(15.0f, 84.0f, futurePuckPos.z));
        }
    }

}
