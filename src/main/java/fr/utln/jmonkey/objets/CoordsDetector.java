package fr.utln.jmonkey.objets;

import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import fr.utln.jmonkey.MainApp;

public class CoordsDetector {

    public static void initKeys() {
        // Mouse left click
        MainApp.instance.getInputManager().addMapping("CoordsDetector",
                new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));
        MainApp.instance.getInputManager().addListener(
                (ActionListener) CoordsDetector::onRightClick,
                "CoordsDetector");

    }

    private static void onRightClick(String name, boolean keyPressed, float tpf) {
        if (!name.equals("CoordsDetector") || keyPressed) return;

        CollisionResults results = new CollisionResults();
        Ray ray = new Ray(MainApp.instance.getCamera().getLocation(),
                MainApp.instance.getCamera().getDirection());
        MainApp.instance.getRootNode().collideWith(ray, results);

        if (results.size() > 0) {
            Vector3f pos = results.getClosestCollision().getContactPoint();
            System.out.println(pos);

        }

    }


}