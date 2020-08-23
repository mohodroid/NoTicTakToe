

/**
 * An ordered board (also known as a <i>sequence</i>). the user of this interface has control
 * over where in the board each index is inserted. The user can access elements by their integer
 * (row, col position in the board), and check for round is finished or not.
 */
public interface BoardGame {

    /**
     * @param x row of the board
     * @param y col of the board
     * @return new instance of ActPlay, that boardgame and player sequence is updated
     */
    ActPlay play(int x, int y);

    ActPlay startNewRound();

    void updatePlayerState(PlayerState[] playerStates);

    void updateMatrix(byte[][] matrix);

    /**
     * @return
     */
    boolean isFinished();

    /**
     * @param x
     * @param y
     * @return
     */
    byte valueOf(int x, int y);

    /**
     * @return
     */
    int getHeight();

    /**
     * @return
     */
    int getWidth();

    /**
     * @return
     */
    byte[][] getBoard();

    PlayerState[] playerState();

}
