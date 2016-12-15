package com.mygdx.game.models.elements;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.models.world.World;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

import static com.mygdx.game.models.elements.MoveableElement.Direction.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by aurelien on 23/11/16.
 */
public class AlienTest {

    private Alien alien;
    private World world;

    @org.junit.Before
    public void setUp() throws Exception {
        this.world = new World();
        this.alien = new Alien(world, new Vector2(8,10), 1);
    }

    @Test
    public void TestValeurConstructeur() {
        assertEquals(new Vector2(8,10), alien.getPosition());
        assertEquals(alien.getNb_pattern(), 2);
        assertEquals(alien.getSpeed(), 4f, 0.001);
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
        new Alien(null, null, 1);
    }

    @Test
    public void testConstructeurHorsMonde() {
        thrown.expect(Exception.class);
        new Alien(this.world, new Vector2(-30,-30), 1);
    }

    @Test
    public void constructeurTestPatternNegatif() throws  Exception{
        thrown.expect(Exception.class);
        new Alien(this.world, new Vector2(10,10), -1);
    }

    @Test
    public void constructeurTestPatternHorsBornePositif() throws  Exception{
        thrown.expect(Exception.class);
        new Alien(this.world, new Vector2(10,10), 6);
    }

    @Test
    public void constructeurTestPatternHorsBorneZero() throws  Exception{
        thrown.expect(Exception.class);
        new Alien(this.world, new Vector2(10,10), 0);
    }

    @Test
    public void constructeurTestMondeNull() throws  Exception{
        thrown.expect(Exception.class);
        new Alien(null, new Vector2(10,10), 2);
    }

    @Test
    public void updateTestSOUTH(){
        alien.setDirection(SOUTH);
        float x = alien.getPosition().x;
        float y = alien.getPosition().y;
        alien.move();
        alien.update(10);
        float new_x = alien.getPosition().x;
        float new_y = alien.getPosition().y;
        float speed = alien.getSpeed();
        assertTrue("NEW_Y = Y - SPEED",new_y==y-speed*10);
        assertTrue("NEW_X = X",new_x==x);
    }

    @Test
    public void TestaddAlienMissile() throws Exception {
        assertEquals(2, world.getElements().size());
        for(int i=0; i<120; i++) {
            alien.update(120);
        }
        assertEquals(3, world.getElements().size());// +1 AlienMissile
    }
}