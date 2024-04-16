package fr.utln.jmonkey.execises;


import com.jme3.app.SimpleApplication;
import com.jme3.bounding.BoundingBox;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

import java.sql.Timestamp;

/** Sample 4 - how to trigger repeating actions from the main event loop.
 * In this example, you use the loop to make the player character
 * rotate continuously. */
public class ExoLoop extends SimpleApplication {

    public static void main(String[] args){
        ExoLoop app = new ExoLoop();
        app.start();
    }

    private float dxyz = 0.1f;
    private boolean inc = true;
    private float maxSize = 3;
    private float currentSize = 1;

    private Geometry cube1, cube2;
    private Box b, b2;


    long time_ms = System.currentTimeMillis();

    @Override
    public void simpleInitApp() {
        for (int x = -10; x < 10; x++) {
            for (int z = -10; z < 10; z++) {
                Box b = new Box(0.5f, 0.5f, 0.5f);
                Geometry g = new Geometry("t", b);
                Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                if ((x + z) % 2 == 0) {
                    mat.setColor("Color", ColorRGBA.Blue);
                } else {
                    mat.setColor("Color", ColorRGBA.Green);
                }
                g.setMaterial(mat);
                g.setLocalTranslation(x, -1, z);
                rootNode.attachChild(g);
            }
        }


        /** this blue box is our player character */
        b = new Box(1, 1, 1);
        cube1 = new Geometry("blue cube", b);
        Material mat = new Material(assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        cube1.setMaterial(mat);


        b2 = new Box(1, 1, 1);
        cube2 = new Geometry("blue cube", b2);
        Material mat2 = new Material(assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");
        mat2.setColor("Color", ColorRGBA.Red);
        cube2.setMaterial(mat2);
        cube2.setLocalTranslation(5, 1, 0);

        rootNode.attachChild(cube1);
        rootNode.attachChild(cube2);
    }

    /* Use the main event loop to trigger repeating actions. */
    @Override
    public void simpleUpdate(float tpf) {
        // make the player rotate:
        cube1.rotate(0, 2*tpf, 0);

        if (Math.abs(currentSize) >= maxSize || currentSize < 1) {
            inc = !inc;
            dxyz = -dxyz;
        }

        currentSize += dxyz;

        b.updateGeometry(new Vector3f(0, 0, 0), currentSize, currentSize, currentSize);

        Material mat;
        if (((time_ms / 1000) & 1) == 0) {
            cube1.getMaterial().setColor("Color", ColorRGBA.Blue);
        } else {
            cube1.getMaterial().setColor("Color", ColorRGBA.Red);
        }
        time_ms = System.currentTimeMillis();
        cube2.move(new Vector3f(tpf, 0.0f, 0.0f));
        cube2.rotate(0.0f, 0.0f, -tpf * FastMath.HALF_PI);

    }
}