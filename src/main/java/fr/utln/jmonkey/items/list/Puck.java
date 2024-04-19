package fr.utln.jmonkey.items.list;

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

}
