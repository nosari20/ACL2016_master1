package com.mygdx.game.models.world;

import com.mygdx.game.models.elements.Alien;
import com.mygdx.game.models.elements.Element;
import com.mygdx.game.models.elements.Spaceship;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by aurelien on 08/12/16.
 */
public class WorldTest {

    private World world;

    @Before
    public void setUp() throws Exception {
        this.world = new World();
    }

    @Test //Le vaisseau est attaqué par plusieurs aliens
    public void moreOneAlienAttackSapaceship() throws  Exception{
        world.createAlien();
        world.createAlien();
        List<Element> elements = world.getElements();
        List<Alien> aliens = new ArrayList<Alien>();

        for(Element e: elements){
            if(e instanceof Alien) {
                aliens.add((Alien) e);
            }
        }

        assertTrue(aliens.size() > 2);
    }

    @Test
    public void createAlien() throws Exception{
        world.createAlien();
        List<Element> elements = world.getElements();
        List<Alien> aliens = new ArrayList<Alien>();

        for(Element e: elements){
            if(e instanceof Alien) {
                aliens.add((Alien) e);
            }
        }

        Alien alien = aliens.get(0);


        assertEquals(2, aliens.size());
        assertTrue(alien.getNb_pattern() > 0);
        assertTrue(alien.getNb_pattern() <= world.getNbDeplacementPattern());

        int min = 0;
        int max = world.getWorldWidth() - 5;
        assertTrue(alien.getPosition().x >= min);
        assertTrue(alien.getPosition().x <= max);
    }

    @Test
    public void addMissileTest() throws Exception {

        world.addMissile();
        world.getSpaceShip().activatePowerUp();
        assertEquals(5, world.getElements().size()); //1 spaceShip + 1 alien + 3 missiles

        world.getSpaceShip().update(100);
        world.addMissile();
        assertEquals(6, world.getElements().size()); //1 spaceShip + 1 alien + 4 missiles
    }

    @Test
    public void addMissileAlien() throws Exception {
        world.addMissileAlien();
        assertEquals(3, world.getElements().size()); //1 spaceShip + 1 alien + 1 missiles
    }

    @Test
    public void incrementAliensDead() throws Exception {
        for(int i=0; i<5; i++){
            world.incrementsAliensdDead();
        }
        assertEquals(5, world.getDeadAliens(), 0.001);
    }

    @Test
    public void testChangeLevel() throws Exception {
        int step = world.getStep();
        for(int i=0; i<step; i++){
            world.incrementsAliensdDead();
        }
        assertEquals(2, world.getLevel(), 0.001);

        step = world.getStep();
        for(int i=0; i<step; i++){
            world.incrementsAliensdDead();
        }
        assertEquals(3, world.getLevel(), 0.001);
    }

    @Test
    public void testLevelChangeAlienSpeed() throws Exception {

        Alien alien1 = (Alien)world.getElements().get(1);
        int step = world.getStep();
        for(int i=0; i<step; i++){
            world.incrementsAliensdDead();
        }

        step = world.getStep();
        for(int i=0; i<step; i++){
            world.incrementsAliensdDead();
        }
        world.createAlien();
        Alien alien2 = (Alien)world.getElements().get(2);
        assertTrue(alien1.getSpeed() < alien2.getSpeed());
    }

    @Test
    public void testLevelChangeAlienLife() throws Exception {

        Alien alien1 = (Alien)world.getElements().get(1);
        int step = world.getStep();
        for(int i=0; i<step; i++){
            world.incrementsAliensdDead();
        }

        step = world.getStep();
        for(int i=0; i<step; i++){
            world.incrementsAliensdDead();
        }
        world.createAlien();
        Alien alien2 = (Alien)world.getElements().get(2);
        assertTrue(alien1.getVie() < alien2.getVie());
    }

    @Test
    public void testLevelChangeActivatePowerUp() throws Exception {

        Spaceship ship = world.getSpaceShip();
        int step = world.getStep();
        for(int i=0; i<step; i++){
            world.incrementsAliensdDead();
        }

        ship.update(1000);

        step = world.getStep();
        for(int i=0; i<step; i++){
            world.incrementsAliensdDead();
        }
        world.createAlien();
        assertTrue(ship.getPowerUp() >0);
    }
}