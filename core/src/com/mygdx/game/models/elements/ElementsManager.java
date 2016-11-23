package com.mygdx.game.models.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aschmat on 23/11/2016.
 */
public class ElementsManager {

    public ArrayList manage(List<Element> elements, Spaceship spaceship, Alien alien, Rectangle worldSurface) {
        ArrayList destroyElement = new ArrayList();
        for(Element e: elements){
            if(e instanceof MoveableElement)
                ((MoveableElement)e).update(Gdx.graphics.getDeltaTime());
            manageCollision(elements, alien, destroyElement, e);
            manageOuts(worldSurface, destroyElement, e);
        }

        return destroyElement;
    }

    private void manageOuts(Rectangle worldSurface, ArrayList destroyElement, Element e) {
        Vector2 v = e.getSize();
        //Verifie si l'objet sort du monde
        if(!worldSurface.overlaps(new Rectangle(e.getPosition().x,e.getPosition().y, v.x, v.y))){
            Gdx.app.log("Left the world", "for always");
            if(!destroyElement.contains(e))
                destroyElement.add(e);
        }
    }

    private void manageCollision(List<Element> elements, Alien alien, ArrayList destroyElement, Element e) {
        if(e instanceof Missile) {
            if (((Missile) e).getExplode() == 0 && alien != null) {
                if (e.hasCollision(alien)) {
                    ((Missile) e).collision();
                    e.setPosition(alien.getPosition());
                    e.setSize(alien.getSize());
                    alien.stop();
                    destroyAlien(elements, alien);
                }
            }
            if ((((Missile) e).getExplode() > 1f))
                destroyElement.add(e);
        }
    }

    public void destroyAlien(List<Element> elements, Alien alien){
        elements.remove(alien);
        alien = null;
    }
}
