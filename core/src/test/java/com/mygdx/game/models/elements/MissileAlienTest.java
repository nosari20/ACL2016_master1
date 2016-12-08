package com.mygdx.game.models.elements;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.models.world.World;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * Created by aurelien on 08/12/16.
 */
public class MissileAlienTest {

    private MissileAlien missileAlien;
    private World world;

    @Before
    public void setUp() throws Exception {
        this.world = new World();
        this.missileAlien = new MissileAlien(world, new Vector2(0.8f,0.8f), MoveableElement.Direction.SOUTH);
    }

    @Test
    public void constructeurTest() throws  Exception{
        assertEquals(new Vector2(0.8f,0.8f), missileAlien.getPosition());
        assertEquals(MoveableElement.Direction.SOUTH, missileAlien.getDirection());
        assertEquals(true, missileAlien.isMoving());
    }

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void TestNullConstructeur() {
        thrown.expect(NullPointerException.class);
        new MissileAlien(null, null, null);
    }

}