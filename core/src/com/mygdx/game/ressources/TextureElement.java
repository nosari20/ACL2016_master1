package com.mygdx.game.ressources;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by brice on 08/12/16.
 */
public class TextureElement {
    private Vector2 size;
    private TextureRegion textureRegion;

    public TextureElement(TextureRegion nTextureRegion, Vector2 nSize){
        this.size = nSize;
        this.textureRegion = nTextureRegion;
    }

    public Vector2 getSize() {
        return size;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }
}
