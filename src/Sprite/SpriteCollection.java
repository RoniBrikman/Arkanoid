//323871723 Roni Brikman
package Sprite;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Sprite collection. Is a collection of all the sprites in a game.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Instantiates a new Sprite collection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Add sprite.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Remove sprite.
     *
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);

    }

    /**
     * calls timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> sprites2 = new ArrayList<Sprite>(this.sprites);
        for (Sprite sprites : sprites2) {
            sprites.timePassed();
        }
    }

    /**
     * Calls DrawOn on all sprites.
     *
     * @param d the d
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprites : this.sprites) {
            sprites.drawOn(d);
        }
    }
}
