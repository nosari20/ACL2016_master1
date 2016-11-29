package com.mygdx.game.models.elements;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.models.world.World;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

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
    public void TestValeurConstructeur() {
        assertEquals(new Vector2(7,15), ship.getPosition());
        assertEquals(ship.getSpeed(), 12f, 0.001);
        assertEquals(ship.isMoving(), false);
        assertEquals(ship.getDirection(), MoveableElement.Direction.NORTH);
        assertEquals(ship.world, this.world);
      //  assertEquals(ship.getAllowedDirection(), Arrays.asList(MoveableElement.Direction.NORTH, MoveableElement.Direction.SOUTH, EST, MoveableElement.Direction.WEST));
    }

    @Test
    public void TestNullConstructeur() {
        thrown.expect(NullPointerException.class);
        Spaceship ship2 = new Spaceship(null, null);
    }

    @Test
    public void updateTestEAST(){
       // ship.setDirection(EST);
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
    public void updateTestWEST(){
      //  ship.setDirection(WEST);
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
    public void updateTestNORTH(){
      //  ship.setDirection(NORTH);
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
    public void updateTestSOUTH(){
      //  ship.setDirection(SOUTH);
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

}