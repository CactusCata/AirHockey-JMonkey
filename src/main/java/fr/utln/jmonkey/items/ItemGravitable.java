package fr.utln.jmonkey.items;

import com.jme3.bullet.BulletAppState;
import com.jme3.scene.Spatial;

public class ItemGravitable extends Item implements Gravitable {

    public ItemGravitable(Spatial model) {
        super(model);
    }

    @Override
    public void applyGravity(BulletAppState bulletAppState) {
        bulletAppState.getPhysicsSpace().add(super.getModel());
    }
}
