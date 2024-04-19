package fr.utln.jmonkey.game;

import com.jme3.math.Vector3f;
import fr.utln.jmonkey.items.list.Table;

import java.util.Random;

public class PuckResetPos {

    public static Vector3f getNext() {
        Random random = new Random();
        if (random.nextBoolean()) {
            return Table.PUCK_ONE_POS;
        } else {
            return Table.PUCK_TWO_POS;
        }
    }

}
