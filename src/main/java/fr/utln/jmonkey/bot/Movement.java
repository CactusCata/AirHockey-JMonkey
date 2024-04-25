package fr.utln.jmonkey.bot;

import com.jme3.math.Vector3f;
import lombok.Getter;

@Getter
public class Movement {

    public static final Movement ZERO = new Movement(MovementType.RELATIVE, 0.5f, 0.5f);

    private final Vector3f movement;
    private final MovementType movementType;

    private Movement(MovementType movementType, float x, float z) {
        this.movement = new Vector3f(x, 0.0f, z);
        this.movementType = movementType;
    }

    public static Movement of(MovementType movementType, float x, float z) {
        return new Movement(movementType, x, z);
    }

}
