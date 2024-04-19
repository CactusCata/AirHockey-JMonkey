package fr.utln.jmonkey.items;

import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.*;
import com.jme3.math.Matrix3f;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import fr.utln.jmonkey.MainApp;
import fr.utln.jmonkey.game.Game;
import fr.utln.jmonkey.game.PlayerNumber;

public class Grabber {

    private static final float MOTION_SCALE = 8.0f;

    private final Node grabable;
    private final Node grabbed;
    private Vector3f lastCorrectPos;
    private Spatial item;

    public Grabber(Node grabable) {
        this.grabable = grabable;
        this.grabbed = new Node("Grabbed");
        MainApp.instance.getRootNode().attachChild(this.grabbed);
        initKeys();
    }

    private void initKeys() {
        // Mouse left click
        MainApp.instance.getInputManager().addMapping("Grab",
                new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        MainApp.instance.getInputManager().addListener(
                (ActionListener) this::onLeftClick,
                "Grab");


        // Mouse move
        MainApp.instance.getInputManager().addMapping("RotateX",
                new MouseAxisTrigger(MouseInput.AXIS_X, true));
        MainApp.instance.getInputManager().addMapping("RotateX_negative",
                new MouseAxisTrigger(MouseInput.AXIS_X, false));

        MainApp.instance.getInputManager().addListener(
                (AnalogListener) this::onMouseMoveEvent,
                "RotateX", "RotateX_negative");
    }

    private void onLeftClick(String name, boolean keyPressed, float tpf) {
        if (!name.equals("Grab") || keyPressed) return;

        if (!grabbed.getChildren().isEmpty()) {
            System.out.println("Grabbed is not empty");
            Spatial s1 = grabbed.getChild(0);
            //RigidBodyControl control = (RigidBodyControl) s1.getControl(0);
            //control.setPhysicsLocation(lastCorrectPos);

            grabbed.detachAllChildren();
            grabable.attachChild(s1);

            item = null;
        } else {
            CollisionResults results = new CollisionResults();
            Ray ray = new Ray(MainApp.instance.getCamera().getLocation(),
                    MainApp.instance.getCamera().getDirection());
            grabable.collideWith(ray, results);

            if (results.size() > 0)
            {
                CollisionResult closest = results.getClosestCollision();
                Spatial s = closest.getGeometry();

                lastCorrectPos = s.getLocalTranslation().clone();
                item = s;

                grabable.detachChild(s);
                grabbed.attachChild(s);
                // make it on the HUD center
            }
        }

    }

    private void onMouseMoveEvent(String name, float value, float tpf) {
        if (item == null) return;

        //this.updateRacketPosition();
    }

    public void onUpdate(float tpf) {
        if (item == null) return;

        this.updateRacketPosition();
    }

    private void updateRacketPosition() {
        CollisionResults results = new CollisionResults();
        Ray ray = new Ray(MainApp.instance.getCamera().getLocation(), MainApp.instance.getCamera().getDirection());
        MainApp.instance.getRootNode().collideWith(ray, results);

        for (int i = 0; i < results.size(); i++) {
            CollisionResult currentCollision = results.getCollision(i);
            //if (currentCollision.getGeometry().getName().equals("model_perfect-geom-0")) {
                lastCorrectPos = currentCollision.getContactPoint().clone();
                Game.instance.correctFuturePos(PlayerNumber.PLAYER_ONE, lastCorrectPos);
                RigidBodyControl control = (RigidBodyControl) item.getControl(0);
                //control.setPhysicsLocation(lastCorrectPos);
                Vector3f itemPos = item.getLocalTranslation();
                Vector3f dir = lastCorrectPos;
                itemPos = dir.subtract(itemPos).mult(new Vector3f(MOTION_SCALE, 0.0f, MOTION_SCALE));
                control.setLinearVelocity(itemPos);
                break;
            //}
        }
    }

}
