package fr.utln.jmonkey.objets;

import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.light.SpotLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import fr.utln.jmonkey.MainApp;

public class Light {

    public static void addSun() {
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(1.0f, -0.7f, 1.0f));
        MainApp.instance.getRootNode().addLight(sun);

        SpotLight spot = new SpotLight(new Vector3f(0, 200, 0), Vector3f.UNIT_Y.mult(-1.0f));
        MainApp.instance.getRootNode().addLight(spot);

        AmbientLight l = new AmbientLight(ColorRGBA.White);
        MainApp.instance.getRootNode().addLight(l);
    }

}
