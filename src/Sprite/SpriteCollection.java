//323871723 Roni Brikman
package Sprite;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * The type src.Sprite collection.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Instantiates a new src.Sprite collection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * calls timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (Sprite sprites : this.sprites) {
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
