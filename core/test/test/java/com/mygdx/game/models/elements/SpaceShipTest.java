package com.mygdx.game.models.elements;

import com.badlogic.gdx.math.Vector2;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aurelien on 10/11/16.
 */
public class SpaceShipTest {

    SpaceShip ship;

    @org.junit.Before
    public void setUp() throws Exception {
        ship = new SpaceShip(new Vector2(7,5));
    }

    @Test
    public void TestValeurConstructeur() {
        assertEquals(new Vector2(7,5), ship.getPosition());
        assertEquals(ship.speed, 7f, 0.001);
        assertEquals(ship.getWidth(), 3, 0.001);
        assertEquals(ship.getLength(), 3, 0.001);
        assertEquals(ship.isMoving, false);
        assertEquals(ship.direction, MoveableElement.Direction.REST);

    }

}