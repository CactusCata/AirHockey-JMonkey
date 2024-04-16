package fr.utln.jmonkey.items;

import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

public class ItemBuilderGravitable extends ItemBuilder {

    private float mass = 0.0f;

    public ItemBuilderGravitable(Vector3f loc, float mass) {
        super(loc);
        setMass(mass);
    }

    public ItemBuilderGravitable setMass(float mass) {
        this.mass = mass;
        return this;
    }

    public ItemBuilderGravitable setParentNode(Node parentNode) {
        super.setParentNode(parentNode);
        return this;
    }

    public ItemBuilderGravitable setScale(float scale) {
        super.setScale(scale);
        return this;
    }

    public ItemBuilderGravitable setInitialRotation(float angle, Vector3f direction) {
        super.setInitialRotation(angle, direction);
        return this;
    }

    public Spatial buildItemAsModel(String modelPath) {
        Spatial model = super.buildMaterialAsModel(modelPath);

        RigidBodyControl rigidBodyControl = new RigidBodyControl(this.mass);
        model.addControl(rigidBodyControl);
        rigidBodyControl.setPhysicsLocation(super.loc);
        rigidBodyControl.setAngularFactor(0.0f);
        rigidBodyControl.setFriction(0.2f);

        if (super.initialRotation != null) {
            rigidBodyControl.setPhysicsRotation(super.initialRotation);
        }

        return model;
    }
}
