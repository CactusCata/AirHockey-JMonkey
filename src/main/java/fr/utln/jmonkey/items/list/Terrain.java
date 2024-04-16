package fr.utln.jmonkey.items.list;

import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import fr.utln.jmonkey.MainApp;
import fr.utln.jmonkey.items.Gravitable;

public class Terrain implements Gravitable {

    private final int width;
    private final int height;
    private final Vector3f loc;

    private Geometry geom;

    public Terrain(int width, int height, Vector3f loc) {
        this.width = width;
        this.height = height;
        this.loc = loc;
    }

    public void draw() {
        Box b = new Box(width / 2, 1, height / 2); // create cube shape
        geom = new Geometry("Box", b);  // create cube geometry from the shape
        Material mat = new Material(MainApp.instance.getAssetManager(),
                "Common/MatDefs/Misc/Unshaded.j3md");  // create a simple material
        mat.setColor("Color", ColorRGBA.Blue);   // set color of material to blue
        geom.setMaterial(mat);

        geom.move(this.loc);

        MainApp.instance.getRootNode().attachChild(geom);

        RigidBodyControl landscape = new RigidBodyControl(0);
        geom.addControl(landscape);
            // make the cube appear in the scene
    }

    @Override
    public void applyGravity(BulletAppState bulletAppState) {
        bulletAppState.getPhysicsSpace().add(geom);
    }

}
