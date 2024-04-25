package fr.utln.jmonkey;

import com.github.stephengold.wrench.LwjglAssetLoader;
import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.environment.EnvironmentCamera;
import com.jme3.environment.LightProbeFactory;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.*;
import com.jme3.light.LightProbe;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.system.AppSettings;
import fr.utln.jmonkey.bot.Bot;
import fr.utln.jmonkey.entities.RivalHollow;
import fr.utln.jmonkey.game.Game;
import fr.utln.jmonkey.game.Player;
import fr.utln.jmonkey.game.PlayerNumber;
import fr.utln.jmonkey.game.PuckResetPos;
import fr.utln.jmonkey.hud.HudScore;
import fr.utln.jmonkey.items.GoalResult;
import fr.utln.jmonkey.items.Goaler;
import fr.utln.jmonkey.items.Grabber;
import fr.utln.jmonkey.items.list.Ground;
import fr.utln.jmonkey.items.list.Puck;
import fr.utln.jmonkey.items.list.Racket;
import fr.utln.jmonkey.items.list.Table;
import fr.utln.jmonkey.objets.*;
import fr.utln.jmonkey.settings.Setting;

public class MainApp extends SimpleApplication {

    public static MainApp instance;
    private Racket r1, r2;
    private Puck puck;
    private Grabber grabber;
    final private Vector3f camDir = new Vector3f();
    final private Vector3f camLeft = new Vector3f();


    private CharacterControl characterControl;
    final private Vector3f walkDirection = new Vector3f();
    private boolean left = false, right = false, up = false, down = false;

    public static void main(String[] args) {
        AppSettings settings = Setting.getSettings();
        instance = new MainApp();
        instance.setSettings(settings);
        instance.start();
    }

    @Override
    public void simpleInitApp() {
        BulletAppState bulletAppState = new BulletAppState();

        Player player = new Player(PlayerNumber.PLAYER_ONE);
        new Game(player, new Bot());
        stateManager.attach(bulletAppState);
        characterControl = player.initPhysic();
        bulletAppState.getPhysicsSpace().add(characterControl);
        setDisplayStatView(false);
        HudScore.initHudScore(guiNode, assetManager);

        CoordsDetector.initKeys();
        setUpKeys();

        Ground ground = new Ground(1000, 1000, new Vector3f(0, 0, 0));
        ground.draw();
        ground.applyGravity(bulletAppState);


        Table table = new Table(0.7f, new Vector3f(0.0f, 5.0f, 0.0f));
        table.applyGravity(bulletAppState);
        //flyCam.setMoveSpeed(10000);


        Node grabbable = new Node("MyRaquet");
        rootNode.attachChild(grabbable);
        r1 = new Racket(10.0f, Table.RACKET_ONE_POS, grabbable);
        r2 = new Racket(10.0f, Table.RACKET_TWO_POS, super.getRootNode());
        r1.applyGravity(bulletAppState);
        r2.applyGravity(bulletAppState);
        puck = new Puck(1.0f, Table.PUCK_ONE_POS);
        puck.applyGravity(bulletAppState);


        grabber = new Grabber(grabbable);
        guiFont = CrossAim.crossAimInit(settings);



        assetManager.registerLoader(LwjglAssetLoader.class, "fbx", "glb");

        Spatial o = RivalHollow.spawn();
        o.setLocalTranslation(new Vector3f(50.0f, 50.0f, 0.0f));
        o.scale(100);
        rootNode.attachChild(o);

        Light.addSun();
        viewPort.setBackgroundColor(new ColorRGBA(0.7f, 0.8f, 1f, 1f));

        final EnvironmentCamera envCam = new EnvironmentCamera(256, new Vector3f(0.0f, 0.0f, 0.0f));
        stateManager.attach(envCam);



    }

    private int frame;
    @Override
    public void simpleUpdate(float tpf) {
        movementUpdate(tpf);
        // PBR - LightProbe initialization
        // Rendering need to be effective before adding LightProbe
        frame++;
        if (frame == 2) {
            final LightProbe probe = LightProbeFactory.makeProbe(stateManager.getState(EnvironmentCamera.class), rootNode);

            probe.getArea().setRadius(100);
            rootNode.addLight(probe);
        }

        if (r1 == null || r2 == null || puck == null) return;

        resetY(r1.getControl());
        resetY(r2.getControl());
        resetY(puck.getControl());
        puck.clampCoords();

        grabber.onUpdate(tpf);

        if (Game.instance.getP2() instanceof Bot bot) {
            bot.updateRacketPos(puck, r2, r1);
        }

        GoalResult goalResult = Goaler.checkGoal(puck);
        if (goalResult != GoalResult.NO_SCORED_GOAL) {
            Game.instance.onPointWon(goalResult);

            RigidBodyControl control = puck.getControl();
            control.setPhysicsLocation(PuckResetPos.getNext());
            control.setLinearVelocity(Vector3f.ZERO);
        }
    }

    private void resetY(RigidBodyControl control) {
        control.setPhysicsLocation(new Vector3f(control.getPhysicsLocation().x, Table.HEIGHT, control.getPhysicsLocation().z));
    }


    /** We over-write some navigational key mappings here, so we can
     * add physics-controlled walking and jumping: */
    private void setUpKeys() {
        inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("Up", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("Down", new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("Jump", new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addListener((ActionListener) this::onAction, "Left");
        inputManager.addListener((ActionListener) this::onAction, "Right");
        inputManager.addListener((ActionListener) this::onAction, "Up");
        inputManager.addListener((ActionListener) this::onAction, "Down");
        inputManager.addListener((ActionListener) this::onAction, "Jump");

    }

    /** These are our custom actions triggered by key presses.
     * We do not walk yet, we just keep track of the direction the user pressed. */
    public void onAction(String binding, boolean value, float tpf) {
        if (binding.equals("Left")) {
            if (value) { left = true; } else { left = false; }
        } else if (binding.equals("Right")) {
            if (value) { right = true; } else { right = false; }
        } else if (binding.equals("Up")) {
            if (value) { up = true; } else { up = false; }
        } else if (binding.equals("Down")) {
            if (value) { down = true; } else { down = false; }
        } else if (binding.equals("Jump")) {
            characterControl.jump();
        }
    }

    /**
     * This is the main event loop--walking happens here.
     * We check in which direction the player is walking by interpreting
     * the camera direction forward (camDir) and to the side (camLeft).
     * The setWalkDirection() command is what lets a physics-controlled player walk.
     * We also make sure here that the camera moves with player.
     */
    public void movementUpdate(float tpf) {
        camDir.set(cam.getDirection()).multLocal(5f);
        camLeft.set(cam.getLeft()).multLocal(5f);
        walkDirection.set(0, 0, 0);
        if (left) {
            walkDirection.addLocal(camLeft);
        }
        if (right) {
            walkDirection.addLocal(camLeft.negate());
        }
        if (up) {
            walkDirection.addLocal(camDir);
        }
        if (down) {
            walkDirection.addLocal(camDir.negate());
        }
        characterControl.setWalkDirection(walkDirection);
        cam.setLocation(characterControl.getPhysicsLocation());
    }

}
