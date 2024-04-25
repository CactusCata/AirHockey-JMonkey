package fr.utln.jmonkey.items.list;

import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import fr.utln.jmonkey.items.ItemBuilderGravitable;
import fr.utln.jmonkey.items.ItemGravitable;

public class Puck extends ItemGravitable {

    public Puck(float scale, Vector3f loc) {
        super(new ItemBuilderGravitable(loc, 1.0f)
                .setScale(scale)
                .setInitialRotation(3 * FastMath.HALF_PI , new Vector3f(1,0,0))
                .buildItemAsModel("Models/AirHockeyPuck/10511_Hockey_puck_v1_L3.obj"));
        super.getControl().setRestitution(0.1f);
    }

    public void clampCoords() {
        Vector3f puckPos = super.getControl().getPhysicsLocation();
        RigidBodyControl control = super.getControl();
        if (puckPos.x < Table.TABLE_POS_MIN_XZ.x) {
            control.setPhysicsLocation(new Vector3f(Table.TABLE_POS_MIN_XZ.x, Table.HEIGHT, puckPos.z));
        } else if (puckPos.x > Table.TABLE_POS_MAX_XZ.x) {
            control.setPhysicsLocation(new Vector3f(Table.TABLE_POS_MAX_XZ.x, Table.HEIGHT, puckPos.z));
        }

        if (puckPos.z < Table.TABLE_POS_MIN_XZ.z) {
            control.setPhysicsLocation(new Vector3f(puckPos.x, Table.HEIGHT, Table.TABLE_POS_MIN_XZ.z));
        } else if (puckPos.z > Table.TABLE_POS_MAX_XZ.z) {
            control.setPhysicsLocation(new Vector3f(puckPos.x, Table.HEIGHT, Table.TABLE_POS_MAX_XZ.z));
        }
    }
}
