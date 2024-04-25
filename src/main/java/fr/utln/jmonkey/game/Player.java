package fr.utln.jmonkey.game;

import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.math.Vector3f;
import lombok.Getter;

public class Player {

    @Getter
    private final PlayerNumber playerNumber;
    @Getter
    private final Score score;

    public Player(PlayerNumber playerNumber) {
        this.playerNumber = playerNumber;
        this.score = new Score();
    }

    public CharacterControl initPhysic() {
        CapsuleCollisionShape capsuleShape = new CapsuleCollisionShape(5.5f, 300f, 1);
        CharacterControl player = new CharacterControl(capsuleShape, 1.05f);
        player.setJumpSpeed(500);
        player.setFallSpeed(500);
        player.setGravity(500);
        player.setPhysicsLocation(new Vector3f(0, 100, 0));
        return player;
    }

}
