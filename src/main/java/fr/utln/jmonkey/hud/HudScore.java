package fr.utln.jmonkey.hud;

import com.atr.jme.font.TrueTypeBMP;
import com.atr.jme.font.TrueTypeFont;
import com.atr.jme.font.asset.TrueTypeKeyBMP;
import com.atr.jme.font.asset.TrueTypeLoader;
import com.atr.jme.font.shape.TrueTypeNode;
import com.atr.jme.font.util.Style;
import com.jme3.asset.AssetManager;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;

public class HudScore {

    public static void initHudScore(Node guiNode, AssetManager assetManager) {
        assetManager.registerLoader(TrueTypeLoader.class, "ttf");

        TrueTypeKeyBMP ttk = new TrueTypeKeyBMP("Interface/Fonts/StonePunk/StonePunk.ttf",
                Style.Plain, 28);
        TrueTypeFont ttf = (TrueTypeBMP)assetManager.loadAsset(ttk);
        ttf.setScale(2.0f);

        TrueTypeNode trueNode = ttf.getText("Hello World", 0, ColorRGBA.White);
        trueNode.setLocalTranslation(0, 480, 0);

        guiNode.attachChild(trueNode);

    }

}
