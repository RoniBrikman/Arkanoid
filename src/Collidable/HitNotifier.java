//323871723 Roni Brikman
package Collidable;

/**
 * The interface Hit notifier. Notifies the listeners when we hit something.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     *
     * @param hl the hl
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hit listener.
     *
     * @param hl the hl
     */
// Remove hl from the list of listeners to hit events.
    void removeHitListener(HitListener hl);
}
