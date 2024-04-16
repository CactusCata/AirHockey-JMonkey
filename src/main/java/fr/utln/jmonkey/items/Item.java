package fr.utln.jmonkey.items;

import com.jme3.scene.Spatial;

public class Item {

    private final Spatial model;

    public Item(Spatial model) {
        this.model = model;
    }

    public Spatial getModel() {
        return this.model;
    }

}
