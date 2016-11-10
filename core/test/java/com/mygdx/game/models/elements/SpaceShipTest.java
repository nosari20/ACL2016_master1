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

    @Test
    public void TestNullConstructeur() {
        SpaceShip ship2 = new SpaceShip(null);
        assertEquals(null, ship2.getPosition());
    }

    package com.mygdx.game.models.elements;

import com.badlogic.gdx.math.Vector2;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

    /**
     * Created by brice on 10/11/16.
     */
    public class SpaceShipTest {
        SpaceShip ship;
        @Before
        public void setUp() throws Exception {
            ship = new SpaceShip(new Vector2(7,15));
        }

        @Test
        public void updateTestEAST(){
            ship.setDirection(EAST);
            int x = ship.getPosition().x;
            int y = ship.getPosition().y;
            ship.move();
            ship.update(10);
            int new_x = ship.getPosition().x;
            int new_y = ship.getPosition().y;
            int speed = getSpeed();
            assertTrue("NEW_Y = Y",y==new_y);
            assertTrue("NEW_X = X + SPEED"new_x==x+speed*10);
        }

        @Test
        public void updateTestWEST(){
            ship.setDirection(WEST);
            int x = ship.getPosition().x;
            int y = ship.getPosition().y;
            ship.move();
            ship.update(10);
            int new_x = ship.getPosition().x;
            int new_y = ship.getPosition().y;
            int speed = getSpeed();
            assertTrue("NEW_Y = Y",y==new_y);
            assertTrue("NEW_X = X - SPEED"new_x==x-speed*10);
        }

        @Test
        public void updateTestNORTH(){
            ship.setDirection(NORTH);
            int x = ship.getPosition().x;
            int y = ship.getPosition().y;
            ship.move();
            ship.update(10);
            int new_x = ship.getPosition().x;
            int new_y = ship.getPosition().y;
            int speed = getSpeed();
            assertTrue("NEW_Y = Y - SPEED",new_y==y - speed*10);
            assertTrue("NEW_X = X "new_x==x);
        }

        @Test
        public void updateTestEAST(){
            ship.setDirection(EAST);
            int x = ship.getPosition().x;
            int y = ship.getPosition().y;
            ship.move();
            ship.update(10);
            int new_x = ship.getPosition().x;
            int new_y = ship.getPosition().y;
            int speed = getSpeed();
            assertTrue("NEW_Y = Y + SPEED",new_y==y+speed*10);
            assertTrue("NEW_X = X"new_x==x);
        }

    }

}