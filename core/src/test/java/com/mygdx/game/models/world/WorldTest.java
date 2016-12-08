package com.mygdx.game.models.world;

import com.mygdx.game.models.elements.Alien;
import com.mygdx.game.models.elements.Element;
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
    public void createAlien() {
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


}