package fr.utln.jmonkey.items;

import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import fr.utln.jmonkey.items.list.Puck;

public class Goaler {

    private static final Vector2f[] MY_GOAL = new Vector2f[] {
            new Vector2f(-11.0f, -89.0f), // minX, minZ
            new Vector2f(11.0f, -82.0f) // maxX, maxZ
    };

    private static final Vector2f[] RIVAL_GOAL = new Vector2f[] {
            new Vector2f(-11.0f, 84.0f), // minX, minZ
            new Vector2f(11.0f, 89.0f) // maxX, maxZ
    };

    public static GoalResult checkGoal(Puck puck) {
        RigidBodyControl rigidBodyControl = (RigidBodyControl) puck.getModel().getControl(0);
        Vector3f puckLocation = rigidBodyControl.getPhysicsLocation();
        if (MY_GOAL[0].x < puckLocation.x && MY_GOAL[0].y < puckLocation.z &&
            MY_GOAL[1].x > puckLocation.x && MY_GOAL[1].y > puckLocation.z) {
            return GoalResult.RIVAL_SCORED_GOAL;
        }

        if (RIVAL_GOAL[0].x < puckLocation.x && RIVAL_GOAL[0].y < puckLocation.z &&
            RIVAL_GOAL[1].x > puckLocation.x && RIVAL_GOAL[1].y > puckLocation.z) {
            return GoalResult.I_SCORED_GOAL;
        }

        return GoalResult.NO_SCORED_GOAL;
    }

}
