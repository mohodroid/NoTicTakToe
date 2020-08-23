
import java.io.Serializable;
import java.util.Arrays;

/**
 * This class represent the current state of the board and player's state.
 *
 * response of the {@link BoardGame#play(int, int)}
 */
public final class ActPlay {

    /**
     * States of the players in the round
     */
    private final PlayerState[] playerStates;

    /**
     * State of the board in the round
     */
    private final byte[][] board;


    public ActPlay(PlayerState[] playerStates, byte[][] board) {
        this.playerStates = playerStates;
        this.board = board;
    }

    public byte[][] getBoard() {
        return board;
    }

    public PlayerState[] getPlayerStates() {
        return playerStates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActPlay actPlay = (ActPlay) o;
        return Arrays.equals(playerStates, actPlay.playerStates) &&
                Arrays.equals(board, actPlay.board);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(playerStates);
        result = 31 * result + Arrays.hashCode(board);
        return result;
    }

    @Override
    public String toString() {
        return "ActPlay{" +
                "pLayerStates=" + Arrays.toString(playerStates) +
                ", board=" + Arrays.toString(board) +
                '}';
    }
}