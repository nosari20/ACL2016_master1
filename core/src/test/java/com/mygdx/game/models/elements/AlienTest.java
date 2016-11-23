package com.mygdx.game.models.elements;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.models.world.World;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by aurelien on 23/11/16.
 */
public class AlienTest {

    private Alien alien;
    private World world;

    @org.junit.Before
    public void setUp() throws Exception {
        this.world = new World();
        this.alien = new Alien(world, new Vector2(8,10));
    }

    @Test
    public void TestValeurConstructeur() {
        assertEquals(new Vector2(8,10), alien.getPosition());
        assertEquals(alien.getSpeed(), 3f, 0.001);
        assertEquals(alien.isMoving(), true);
        assertEquals(alien.getDirection(), MoveableElement.Direction.NORTH);
        assertEquals(alien.world, this.world);
        assertEquals(alien.getAllowedDirection(), Arrays.asList(MoveableElement.Direction.NORTH, MoveableElement.Direction.SOUTH, MoveableElement.Direction.EST, MoveableElement.Direction.WEST));
        assertEquals(alien.isChangeDirection(), true);
    }

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void TestNullConstructeur() {
        thrown.expect(NullPointerException.class);
        Alien alien2 = new Alien(null, null);
    }

}