package fr.utln.jmonkey.settings;

import com.jme3.system.AppSettings;

import java.awt.*;

public class Setting {

    public static AppSettings getSettings() {
        AppSettings settings = new AppSettings(true);
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        DisplayMode[] modes = device.getDisplayModes();
        int i=0; // note: there are usually several, let's pick the first
        settings.setResolution(modes[i].getWidth(),modes[i].getHeight());
        settings.setFrequency(modes[i].getRefreshRate());
        settings.setBitsPerPixel(modes[i].getBitDepth());
        //settings.setFullscreen(device.isFullScreenSupported());
        settings.setFullscreen(true);

        return settings;
    }

}
