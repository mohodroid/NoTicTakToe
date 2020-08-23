
public enum BoardStates {
    EMPTY((byte) 0),

    /**
     * Fill value for board
     */
    FILL_DEFAULT((byte) 3),
    FILL_PLAYER_ONE((byte) 4),
    FILL_PLAYER_TWO((byte) 5),

    /**
     * Line values for PLAYER_ONE
     */
    LINE_PLAYER_ONE((byte) 6),
    NEW_LINE_PLAYER_ONE((byte) 7),
    NEW_LINE_PLAYER_ONE_FACTOR((byte) 8),
    /**
     * Line values for PLAYER_TWO
     */
    LINE_PLAYER_TWO((byte) 9),
    NEW_LINE_PLAYER_TWO((byte) 10),
    NEW_LINE_PLAYER_TWO_FACTOR((byte) 11);

    private final byte value;


    public byte getValue() {
        return value;
    }

    BoardStates(byte value) {
        this.value = value;
    }
}