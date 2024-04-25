package fr.utln.jmonkey.items.list;

import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.control.Control;
import com.jme3.scene.shape.Box;
import fr.utln.jmonkey.MainApp;

public class Ground {

    private final int width;
    private final int height;
    private final Vector3f loc;
    private Geometry geom;

    public Ground(int width, int height, Vector3f loc) {
        this.width = width;
        this.height = height;
        this.loc = loc;
    }

    public void draw() {
        Box b = new Box(width / 2, 1, height / 2); // create cube shape
        this.geom = new Geometry("Box", b);  // create cube geometry from the shape
        geom.move(this.loc);
        Material mat_brick = new Material(MainApp.instance.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat_brick.setTexture("ColorMap", MainApp.instance.getAssetManager().loadTexture("Textures/Terrain/splat/grass.jpg"));

        this.geom.setMaterial(mat_brick);                   // set the cube's material
        MainApp.instance.getRootNode().attachChild(this.geom);              // make the cube appear in the scene

        RigidBodyControl landscape = new RigidBodyControl(0);
        geom.addControl(landscape);
    }


    public void applyGravity(BulletAppState bulletAppState) {
        bulletAppState.getPhysicsSpace().add(geom);
    }

}
