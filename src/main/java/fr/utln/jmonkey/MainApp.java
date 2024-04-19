package fr.utln.jmonkey;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
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

public class MainApp extends SimpleApplication {

    public static MainApp instance;
    private static Racket r1, r2;
    private static Puck puck;
    private static Game game;
    private static Grabber grabber;

    public static void main(String[] args) {
        instance = new MainApp();
        instance.start();
    }

    @Override
    public void simpleInitApp() {
        new Game(new Player(PlayerNumber.PLAYER_ONE), new Bot());
        BulletAppState bulletAppState = new BulletAppState();
        bulletAppState.setDebugEnabled(true);
        stateManager.attach(bulletAppState);
        setDisplayStatView(false);
        HudScore.initHudScore(guiNode, assetManager);

        CoordsDetector.initKeys();

        Ground ground = new Ground(1000, 1000, new Vector3f(0, 0, 0));
        ground.draw();


        Table table = new Table(0.7f, new Vector3f(0.0f, 5.0f, 0.0f));
        table.applyGravity(bulletAppState);


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

        Spatial o = RivalHollow.spawn();
        o.setLocalTranslation(new Vector3f(50.0f, 50.0f, 0.0f));
        o.scale(10000);
        rootNode.attachChild(o);

        Light.addSun();
        viewPort.setBackgroundColor(new ColorRGBA(0.7f, 0.8f, 1f, 1f));
        cam.setLocation(new Vector3f(0.0f, 100.0f, 0.0f));
        cam.lookAt(new Vector3f(0.0f, 0.0f, 0.0f), Vector3f.UNIT_Y);
        flyCam.setMoveSpeed(100);

    }

    @Override
    public void simpleUpdate(float tpf) {
        if (r1 == null || r2 == null || puck == null) return;

        resetY(r1.getControl());
        resetY(r2.getControl());
        resetY(puck.getControl());

        grabber.onUpdate(tpf);

        if (Game.instance.getP2() instanceof Bot bot) {
            bot.updateRacketPos(puck, r2);
        }

        GoalResult goalResult = Goaler.checkGoal(puck);
        if (goalResult != GoalResult.NO_SCORED_GOAL) {
            Game.instance.onPointWon(goalResult);

            RigidBodyControl control = puck.getControl();
            control.setPhysicsLocation(PuckResetPos.getNext());
            control.setLinearVelocity(Vector3f.ZERO);
        }
    }

    private static void resetY(RigidBodyControl control) {
        Vector3f new_pos = control.getPhysicsLocation().multLocal(1,0,1).add(0.0f, 55.0f, 0.0f);
        control.setPhysicsLocation(new_pos);
    }

}
