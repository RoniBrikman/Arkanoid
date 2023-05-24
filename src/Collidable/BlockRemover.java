package Collidable;
import Game.Game;
import Sprite.Ball;
import Game.Counter;

public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    public BlockRemover(Game game, Counter removedBlocks) {
        this.remainingBlocks = removedBlocks;
        this.game = game;
    }

    // Blocks that are hit should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    public void hitEvent(Block beingHit, Ball hitter) {
        this.game.removeSprite(beingHit);
        this.game.removeCollidable(beingHit);
        beingHit.removeHitListener(this);
        this.remainingBlocks = new Counter(this.remainingBlocks.getValue() - 1);
        this.game.getCounter().decrease(1);
    }
    
    public Counter getCounter (){
        return this.remainingBlocks;
    }
}