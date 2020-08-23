
final public class InitBoardState {

    /**
     * States of the players in the round
     */
    private final PlayerState[] playerStates;

    /**
     * State of the board in the round
     */
    private final byte[][] board;

    public InitBoardState(PlayerState[] playerStates, byte[][] board) {
        this.playerStates = playerStates;
        this.board = board;
    }

    public byte[][] getBoard() {
        return board;
    }

    public PlayerState[] getPlayerStates() {
        return playerStates;
    }

}
