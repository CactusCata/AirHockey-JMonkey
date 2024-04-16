package fr.utln.jmonkey.items.list;

import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import fr.utln.jmonkey.items.ItemBuilderGravitable;
import fr.utln.jmonkey.items.ItemGravitable;

public class Racket extends ItemGravitable {

    public Racket(float scale, Vector3f loc, Node parentNode) {
        super(new ItemBuilderGravitable(loc, 1.0f)
                .setScale(scale)
                .setParentNode(parentNode)
                .buildItemAsModel("Models/Air Hockey Paddle/hockeypaddle.obj"));
    }

}
