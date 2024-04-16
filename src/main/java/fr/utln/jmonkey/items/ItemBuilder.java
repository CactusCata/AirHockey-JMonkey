package fr.utln.jmonkey.items;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import fr.utln.jmonkey.MainApp;

public class ItemBuilder {

    private Node parentNode;
    private float scale;
    protected Vector3f loc;
    protected Quaternion initialRotation;

    public ItemBuilder(Vector3f loc) {
        this.setLocation(loc);
        this.setParentNode(MainApp.instance.getRootNode());
        this.setScale(1.0f);
        this.initialRotation = null;
    }

    public ItemBuilder setLocation(Vector3f location) {
        this.loc = location;
        return this;
    }

    public ItemBuilder setParentNode(Node parentNode) {
        this.parentNode = parentNode;
        return this;
    }

    public ItemBuilder setScale(float scale) {
        this.scale = scale;
        return this;
    }

    public ItemBuilder setInitialRotation(float angle, Vector3f direction) {
        this.initialRotation = new Quaternion();
        this.initialRotation.fromAngleAxis(angle, direction);
        return this;
    }

    public Spatial buildMaterialAsModel(String modelPath) {
        Spatial model = MainApp.instance.getAssetManager().loadModel(modelPath);
        model.move(this.loc);
        model.scale(this.scale);

        if (this.initialRotation != null) {
            model.setLocalRotation(this.initialRotation);
        }

        parentNode.attachChild(model);
        return model;
    }

}
