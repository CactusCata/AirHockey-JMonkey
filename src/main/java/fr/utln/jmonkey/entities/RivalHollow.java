package fr.utln.jmonkey.entities;

import com.jme3.anim.Armature;
import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetKey;
import com.jme3.asset.AssetLoader;
import com.jme3.material.Material;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.plugins.fbx.FbxLoader;
import fr.utln.jmonkey.MainApp;

import java.io.*;

public class RivalHollow {

    public static Spatial spawn() {
        //Node node = (Node) MainApp.instance.getAssetManager().loadModel("Models/rivals/lose.glb");
        Spatial node = MainApp.instance.getAssetManager().loadModel("Models/defeated.glb");
        //node.setCullHint(Spatial.CullHint.Never);

        return node;
    }

}
