//323871723 Roni Brikman

package Sprite;

import Collidable.Block;
import Collidable.HitListener;
import Game.Counter;
import Game.Game;

/**
 * The type Ball remover. Remove balls when it calls.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * Instantiates a new Ball remover.
     *
     * @param game         the game
     * @param removedBalls the removed balls
     */
    public BallRemover(Game game, Counter removedBalls) {
        this.remainingBalls = removedBalls;
        this.game = game;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls = new Counter(this.remainingBalls.getValue() - 1);
        this.game.getBallsCounter().decrease(1);
        
    }
}
