package Game;
public class Counter {
    private int count;

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
     * Gets current count...
     *
     * @return the count
     */
    public int getValue() {
        return this.count;

    }
}
