package com.mygdx.game.models.elements;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.models.world.World;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

import static com.mygdx.game.models.elements.MoveableElement.Direction.*;
import static org.junit.Assert.*;


public class SpaceshipTest {

    Spaceship ship;
    World world;

    @org.junit.Before
    public void setUp() throws Exception {
        world = new World();
        ship = new Spaceship(world, new Vector2(7,15));
    }

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void TestValeurConstructeur() throws Exception{
        assertEquals(new Vector2(7,15), ship.getPosition());
        assertEquals(ship.getSpeed(), 12f, 0.001);
        assertEquals(ship.isMoving(), false);
        assertEquals(ship.getDirection(), NORTH);
        assertEquals(ship.world, this.world);
        assertEquals(ship.getAllowedDirection(), Arrays.asList(NORTH, SOUTH, EST, MoveableElement.Direction.WEST));
    }

    @Test
    public void TestNullConstructeur() throws Exception{
        thrown.expect(NullPointerException.class);
        new Spaceship(null, null);
    }

    @Test
    public void TestConstructeurHorsMonde() throws Exception{
        thrown.expect(Exception.class);
        new Spaceship(world, new Vector2(-30,-30));
    }

    @Test
    public void testConstructeurWorldNull() throws Exception{
        thrown.expect(Exception.class);
        new Spaceship(null, new Vector2(-30,-30));
    }

    @Test
    public void updateTestEAST() throws Exception{
        ship.setDirection(EST);
        float x = ship.getPosition().x;
        float y = ship.getPosition().y;
        ship.move();
        ship.update(10);
        float new_x = ship.getPosition().x;
        float new_y = ship.getPosition().y;
        float speed = ship.getSpeed();
        assertTrue("NEW_Y = Y",y==new_y);
        assertTrue("NEW_X = X + SPEED",new_x==x+speed*10);
    }

    @Test
    public void updateTestWEST() throws Exception{
        ship.setDirection(WEST);
        float x = ship.getPosition().x;
        float y = ship.getPosition().y;
        ship.move();
        ship.update(10);
        float new_x = ship.getPosition().x;
        float new_y = ship.getPosition().y;
        int speed = ship.getSpeed();
        assertTrue("NEW_Y = Y",y==new_y);
        assertTrue("NEW_X = X - SPEED",new_x==x-speed*10);
    }

    @Test
    public void updateTestNORTH() throws Exception{
        ship.setDirection(NORTH);
        float x = ship.getPosition().x;
        float y = ship.getPosition().y;
        ship.move();
        ship.update(10);
        float new_x = ship.getPosition().x;
        float new_y = ship.getPosition().y;
        float speed = ship.getSpeed();
        assertTrue("NEW_Y = Y + SPEED",new_y==y + speed*10);
        assertTrue("NEW_X = X ",new_x==x);
    }

    @Test
    public void updateTestSOUTH() throws Exception{
        ship.setDirection(SOUTH);
        float x = ship.getPosition().x;
        float y = ship.getPosition().y;
        ship.move();
        ship.update(10);
        float new_x = ship.getPosition().x;
        float new_y = ship.getPosition().y;
        float speed = ship.getSpeed();
        assertTrue("NEW_Y = Y - SPEED",new_y==y-speed*10);
        assertTrue("NEW_X = X",new_x==x);
    }

    @Test
    public void destroy() throws Exception {
        ship.destroy(10);
        assertEquals(100, ship.getVie(), 0.0001);
        assertEquals(1, ship.getNbKilledAliens());
    }

    @Test
    public void destroyNegatif() throws Exception {
        ship.destroy(-10);
        assertEquals(100, ship.getVie(), 0.001);
    }

    @Test
    public void testTouched() throws Exception{
        ship.setVie(30);
        ship.touched(20);

        assertEquals(10, ship.getVie(), 0.001);
    }

    @Test
    public void testTouchedDead() throws Exception{
        ship.setVie(30);
        ship.touched(40);

        assertTrue(ship.isDead());
    }

    @Test
    public void addVie() throws Exception {
        ship.addVie(1000);
        assertEquals(100, ship.getVie(), 0.001);

        ship.setVie(30);
        ship.addVie(10);
        assertEquals(40, ship.getVie(), 0.001);

    }

    @Test
    public void addVieNegatif() throws Exception {
        ship.addVie(-30);
        assertEquals(100, ship.getVie(), 0.001);
    }
}