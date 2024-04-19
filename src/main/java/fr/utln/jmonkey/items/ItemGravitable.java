package fr.utln.jmonkey.items;

import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.scene.Spatial;
import lombok.Getter;

public class ItemGravitable extends Item implements Gravitable {

    @Getter
    private RigidBodyControl control;

    public ItemGravitable(Spatial model) {
        super(model);
        this.control = (RigidBodyControl) model.getControl(0);
    }

    @Override
    public void applyGravity(BulletAppState bulletAppState) {
        bulletAppState.getPhysicsSpace().add(super.getModel());
    }
}
