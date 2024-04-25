package fr.utln.jmonkey.items.list;

import com.jme3.math.Vector3f;
import fr.utln.jmonkey.items.ItemBuilderGravitable;
import fr.utln.jmonkey.items.ItemGravitable;

public class Table extends ItemGravitable {

    public static final Vector3f RACKET_ONE_POS = new Vector3f(0.0f, 58.0f, -62.0f);
    public static final Vector3f RACKET_TWO_POS = new Vector3f(0.0f, 58.0f, +62.0f);
    public static final Vector3f PUCK_ONE_POS = new Vector3f(0.0f, 58.0f, -30.0f);
    public static final Vector3f PUCK_TWO_POS = new Vector3f(0.0f, 58.0f, 30.0f);

    public static final Vector3f TABLE_POS_MIN_XZ = new Vector3f(-40.0f, 58.0f, -84.0f);
    public static final Vector3f TABLE_POS_MAX_XZ = new Vector3f(40.0f, 58.0f, 84.0f);
    public static final float HEIGHT = 55.0f;

    public Table(float scale, Vector3f loc) {
        super(new ItemBuilderGravitable(loc, 0.0f)
                .setScale(scale)
                .buildItemAsModel("Models/table1/model_perfect.obj"));
        super.getControl().setRestitution(0.3f);
    }

}