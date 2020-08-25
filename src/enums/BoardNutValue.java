package enums;

public enum BoardNutValue {

    EMPTY(0),
    /**
     * Fill values
     */
    FILL(1),

    /**
     * Line values
     */
    LINE_PLAYER_ONE(4),
    LINE_PLAYER_TWO(5);


    private final int value;

    BoardNutValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}