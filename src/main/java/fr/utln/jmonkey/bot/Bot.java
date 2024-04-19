package fr.utln.jmonkey.bot;

import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import fr.utln.jmonkey.game.Game;
import fr.utln.jmonkey.game.Player;
import fr.utln.jmonkey.game.PlayerNumber;
import fr.utln.jmonkey.items.list.Puck;
import fr.utln.jmonkey.items.list.Racket;

public class Bot extends Player {

    public Bot() {
        super(PlayerNumber.PLAYER_TWO);
    }

    public void updateRacketPos(Puck puck, Racket racket) {
        Vector3f puckLocation = puck.getControl().getPhysicsLocation().clone();
        Game.instance.correctFuturePos(PlayerNumber.PLAYER_TWO, puckLocation);
        Vector3f itemPos = racket.getControl().getPhysicsLocation();
        itemPos = puckLocation.subtract(itemPos).mult(new Vector3f(4.0f, 0.0f, 4.0f));
        racket.getControl().setLinearVelocity(itemPos);
    }

}
