//323871723 Roni Brikman
package Game;
import Sprite.Sprite;
import biuoop.DrawSurface;

/**
 * The type Score indicator. In charge of displaying the current score.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * Instantiates a new Score indicator.
     *
     * @param score the score COUNTER
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(350, 15, "Score: " + (this.score.getValue()), 15);
    }

    @Override
    public void timePassed() {
    }
}
