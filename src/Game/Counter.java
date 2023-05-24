//323871723 Roni Brikman
package Game;

/**
 * The type Counter. It can count things we initialize it to count.
 */
public class Counter {
    private int count;

    /**
     * Instantiates a new Counter.
     *
     * @param count the start number
     */
    public Counter(int count) {
        this.count = count;
    }

    /**
     * Increase number from current count.
     *
     * @param number the number to add
     */
    public void increase(int number) {
        this.count = this.count + number;
    }

    /**
     * subtract number from current count.
     *
     * @param number the number do subtract
     */
    public void decrease(int number) {
        this.count = this.count - number;
    }

    /**
     * Gets the current count number.
     *
     * @return the count
     */
    public int getValue() {
        return this.count;

    }
}
