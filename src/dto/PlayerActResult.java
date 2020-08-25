package dto;

import java.util.Arrays;

/**
 * This class represent the current state of the board and player's state.
 */
public final class PlayerActResult {

    /**
     * States of the players in the round
     */
    private final PlayerStatus[] playerStatuses;

    /**
     * State of the board in the round
     */
    private final int[][] gameBoard;


    public PlayerActResult(PlayerStatus[] playerStatuses, int[][] gameBoard) {
        this.playerStatuses = playerStatuses;
        this.gameBoard = gameBoard;
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }

    public PlayerStatus[] getPlayerStatuses() {
        return playerStatuses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerActResult playerActResult = (PlayerActResult) o;
        return Arrays.equals(playerStatuses, playerActResult.playerStatuses) &&
                Arrays.equals(gameBoard, playerActResult.gameBoard);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(playerStatuses);
        result = 31 * result + Arrays.hashCode(gameBoard);
        return result;
    }

    @Override
    public String toString() {
        return "dto.ActPlay{" +
                "pLayerStates=" + Arrays.toString(playerStatuses) +
                ", board=" + Arrays.toString(gameBoard) +
                '}';
    }
}