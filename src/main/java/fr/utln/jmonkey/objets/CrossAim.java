package fr.utln.jmonkey.objets;

import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.system.AppSettings;
import fr.utln.jmonkey.MainApp;

public class CrossAim {

    public static BitmapFont crossAimInit(AppSettings settings) {
        BitmapFont guiFont = MainApp.instance.getAssetManager().loadFont("Interface/Fonts/Default.fnt");
        BitmapText ch = new BitmapText(guiFont, false);
        ch.setSize(guiFont.getCharSet().getRenderedSize() * 2);
        ch.setText("+");
        ch.setLocalTranslation(
                settings.getWidth() / 2 - ch.getLineWidth() / 2,
                settings.getHeight() / 2 + ch.getLineHeight() / 2,
                0);
        MainApp.instance.getGuiNode().attachChild(ch);
        return guiFont;
    }

}
