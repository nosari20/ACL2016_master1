package com.mygdx.game.models.elements;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.models.world.World;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.mygdx.game.models.elements.MoveableElement.Direction.NORTH;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by aurelien on 07/12/16.
 */
public class MissileTest {

    private World world;
    private Missile missile;

    @Before
    public void setUp() throws Exception {
        this.world = new World();
        this.missile = new Missile(this.world, new Vector2(0.8f,0.8f), MoveableElement.Direction.SOUTH);
    }

    @Test
    public void testConstructeur() throws  Exception {
        assertEquals(new Vector2(0.8f,0.8f), missile.getPosition());
        assertEquals(MoveableElement.Direction.SOUTH, missile.getDirection());
        assertEquals(true, missile.isMoving());
    }

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void testConstructeurNull() {
        thrown.expect(NullPointerException.class);
        new Missile(null, null, null);
    }

    @Test
    public void collision() throws Exception {
        missile.collision();
        assertEquals(false, missile.isMoving());
        assertEquals(0.01f, missile.getExplode(), 0.001);
    }

    @Test
    public void inverseDirection() throws Exception {
        missile.inverseDirection(MoveableElement.Direction.WEST);
        assertEquals(MoveableElement.Direction.NORTHEAST, missile.getDirection());

        missile.inverseDirection(MoveableElement.Direction.EST);
        assertEquals(MoveableElement.Direction.NORTHWEST, missile.getDirection());
    }

    @Test
    public void updateTestNorth(){
        missile.setDirection(NORTH);
        float x = missile.getPosition().x;
        float y = missile.getPosition().y;
        missile.move();
        missile.update(10);
        float newX = missile.getPosition().x;
        float newY = missile.getPosition().y;
        int speed = missile.getSpeed();
        assertTrue(newX == x);
        assertTrue(newY == y + (float) speed * 10);
    }
}