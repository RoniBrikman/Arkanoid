//323871723 Roni Brikman
package Collidable;
import Game.Game;
import Sprite.Ball;
import Game.Counter;

/**
 * The type Block remover. Remove blocks from the game.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param game          the game played
     * @param removedBlocks the removed blocks Counter
     */
    public BlockRemover(Game game, Counter removedBlocks) {
        this.remainingBlocks = removedBlocks;
        this.game = game;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
        this.remainingBlocks = new Counter(this.remainingBlocks.getValue() - 1);
        this.game.getBlocksCounter().decrease(1);
    }
}