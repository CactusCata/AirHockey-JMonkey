package fr.utln.jmonkey.hud;

import com.atr.jme.font.TrueTypeBMP;
import com.atr.jme.font.TrueTypeFont;
import com.atr.jme.font.asset.TrueTypeKeyBMP;
import com.atr.jme.font.asset.TrueTypeLoader;
import com.atr.jme.font.shape.TrueTypeNode;
import com.atr.jme.font.util.Style;
import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;
import fr.utln.jmonkey.game.Game;

public class HudScore {

    private static TrueTypeNode scoreLabel;

    public static void initHudScore(Node guiNode, AssetManager assetManager) {
        assetManager.registerLoader(TrueTypeLoader.class, "ttf");

        int desiredPointSize = 50;
        float scale = 0.0f;
        int actualSize = 0;
        if (desiredPointSize < 32) {
            actualSize = (int)Math.floor(desiredPointSize / 0.73f);
            scale = desiredPointSize / (float)actualSize;
        } else if (desiredPointSize < 53) {
            actualSize = (int)Math.floor(desiredPointSize / 0.84f);
            scale = desiredPointSize / (float)actualSize;
        }


        TrueTypeKeyBMP ttk = new TrueTypeKeyBMP("Interface/Fonts/StonePunk/StonePunk.ttf",
                Style.Plain, actualSize);
        TrueTypeFont ttf = assetManager.loadAsset(ttk);
        ttf.setScale(scale);
        scoreLabel = ttf.getText("Score: [%d - %d]".formatted(
                Game.instance.getP1().getScore().getPointAmount(),
                        Game.instance.getP2().getScore().getPointAmount()),
                0, ColorRGBA.Green);
        scoreLabel.setLocalTranslation(0, 480, 0);
        guiNode.attachChild(scoreLabel);

    }

    public static void onScoreUpdated() {
        scoreLabel.setText("Score: [%d - %d]".formatted(
            Game.instance.getP1().getScore().getPointAmount(),
            Game.instance.getP2().getScore().getPointAmount()));
        scoreLabel.updateGeometry();
    }

}
