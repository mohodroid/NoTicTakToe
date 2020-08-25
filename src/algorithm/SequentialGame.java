package algorithm;

import dto.PlayerActResult;
import dto.PlayerStatus;
import enums.Player;

/**
 * An ordered board (also known as a <i>sequence</i>). the user of this interface has control
 * over where in the board each index is inserted. The user can access elements by their integer
 * (row, col position in the board), and check for round is finished or not.
 */
public interface SequentialGame {
    /**
     * @param x row of the board
     * @param y col of the board
     * @return new instance of dto.ActPlay, that boardgame and player sequence is updated
     */

    PlayerActResult play(int x, int y, Player player) throws IllegalArgumentException;

    void updatePlayersStatus(PlayerStatus[] playerStatuses);

    void updateMatrix(int[][] gameBoard);

    boolean isFinished();

    int valueOf(int x, int y);

    int getCol();

    int getRaw();

    int[][] getGameBoard();

    PlayerStatus[] getPlayersStatus();

}
