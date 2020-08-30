package algorithm;

import dto.PlayerActResult;
import dto.PlayerStatus;
import enums.BoardNutValue;
import enums.Player;

import java.util.Arrays;

public final class NoTicTakToe implements SequentialGame {
    //number of column and row in the game board
    private final int row, col;
    private int[][] gameBoard;
    private PlayerStatus[] playersStatus;

    public NoTicTakToe(int row, int col) {
        this.row = row;
        this.col = col;
        gameBoard = new int[row][col];
        for (int[] rows : gameBoard) {
            Arrays.fill(rows, BoardNutValue.EMPTY.getValue());
        }
        initPlayersStatus();
    }

    @Override
    public void updateMatrix(int[][] matrix) {
        this.gameBoard = matrix;
    }

    @Override
    public void updatePlayersStatus(PlayerStatus[] playersStatus) {
        this.playersStatus = playersStatus;
    }

    @Override
    public PlayerStatus[] getPlayersStatus() {
        return playersStatus;
    }

    @Override
    public int getCol() {
        return col;
    }

    @Override
    public int getRaw() {
        return row;
    }

    @Override
    public int[][] getGameBoard() {
        return gameBoard;
    }

    @Override
    public PlayerActResult play(int x, int y, Player player) {
        //validation
//        validation(x, y, player);
        //fill board with player one nut or player two nut
        gameBoard[x][y] = BoardNutValue.FILL.getValue();
        //update player states and board
        int numberOfLines = isCreateLine(player, x, y);
        updatePlayersStatus(numberOfLines);
        return new PlayerActResult(playersStatus, gameBoard);
    }

    private void validation(int x, int y, Player player) {
        if (!playersStatus[player.getPlayer() - 1].isTurn())
            throw new IllegalArgumentException("its not " + player + " turn");
        if (x < 0 || x > row || y < 0 || y > col)
            throw new IllegalArgumentException("x or y is no in correct range");
        if (gameBoard[x][y] != BoardNutValue.EMPTY.getValue())
            throw new IllegalArgumentException("gameBoard index is not empty");

    }

    private void initPlayersStatus() {
        playersStatus = new PlayerStatus[2];
        playersStatus[0] = new PlayerStatus(Player.PLAYER_ONE, 0, true);
        playersStatus[1] = new PlayerStatus(Player.PLAYER_TWO, 0, false);
    }

    private void updatePlayersStatus(int numberOfLines) {
        if (playersStatus[0].isTurn()) {
            int totalLine = playersStatus[0].getLines() + numberOfLines;
            playersStatus[0] = new PlayerStatus(playersStatus[0].getPlayer(), totalLine, false);
            playersStatus[1] = new PlayerStatus(playersStatus[1].getPlayer(), playersStatus[1].getLines(), true);
        } else if (playersStatus[1].isTurn()) {
            int totalLine = playersStatus[1].getLines() + numberOfLines;
            playersStatus[0] = new PlayerStatus(playersStatus[0].getPlayer(), playersStatus[0].getLines(), true);
            playersStatus[1] = new PlayerStatus(playersStatus[1].getPlayer(), totalLine, false);
        }
    }

    private int isCreateLine(Player player, int x, int y) {
        // should traverse
        int up = 0;
        int up_right = 0;
        int right = 0;
        int btm_right = 0;
        int btm = 0;
        int btm_left = 0;
        int left = 0;
        int up_left = 0;
        int numberOfLine = 0;
        // iterate to up to looking for Fill state{col is static}
        for (int i = x - 1; i >= x - 2 && i >= 0; i--) {
            if (gameBoard[i][y] == BoardNutValue.FILL.getValue()) {
                up++;
            } else break;
        }
        //iterate to btm to looking for fill state{col is static}
        for (int i = x + 1; i <= x + 2 && i < row; i++) {
            if (gameBoard[i][y] == BoardNutValue.FILL.getValue()) {
                btm++;
            } else break;
        }
        //iterate to right to looking for fill state{row is static}
        for (int i = y + 1; i <= y + 2 && i < col; i++) {
            if (gameBoard[x][i] == BoardNutValue.FILL.getValue()) {
                right++;
            } else break;
        }
        //iterate to left to looking for fill state{row is static}
        for (int i = y - 1; i >= y - 2 && i >= 0; i--) {
            if (gameBoard[x][i] == BoardNutValue.FILL.getValue()) {
                left++;
            } else break;
        }
        //iterate to up-right to looking for fill state{nor row and col is static}
        for (int i = x - 1; i >= x - 2 && i >= 0; i--) {
            int colY = y + up_right + 1;
            if (colY >= col) break;
            int value = gameBoard[i][colY];
            if (value == BoardNutValue.FILL.getValue()) {
                up_right++;
            } else break;
        }
        //iterate to btm_right to looking for fill states(nor row and col is static)
        for (int i = y + 1; i <= y + 2 && i < col; i++) {
            int rowX = x + btm_right + 1;
            if (rowX >= row) break;
            int value = gameBoard[rowX][i];
            if (value == BoardNutValue.FILL.getValue()) {
                btm_right++;
            } else break;
        }
        //iterate to up-left to looking for fill states(nor row and col is static)
        for (int i = y - 1; i >= y - 2 && i >= 0; i--) {
            int rowX = x - 1 - up_left;
            if (rowX < 0) break;
            int value = gameBoard[rowX][i];
            if (value == BoardNutValue.FILL.getValue()) {
                up_left++;
            } else break;
        }
        //iterate to btm-left  to looking for fill st{ates(nor row and col is static)
        for (int i = x + 1; i <= x + 2 && i < row; i++) {
            int colY = y - 1 - btm_left;
            if (colY < 0) break;
            int value = gameBoard[i][colY];
            if (value == BoardNutValue.FILL.getValue()) {
                btm_left++;
            } else break;
        }
        //check for creating line up to btm
        if (up + btm + 1 >= 3) {
            for (int i = x - up; i <= x + btm; i++) {
                gameBoard[i][y] = BoardNutValue.LINE.getValue();
            }
            numberOfLine++;
        }
        //check for creating line left to right
        if (right + left + 1 >= 3) {
            for (int i = y - left; i <= y + right; i++) {
                gameBoard[x][i] = BoardNutValue.LINE.getValue();
            }
            numberOfLine++;
        }
        //check for creating line form up-right to btm-left
        if (up_right + btm_left + 1 >= 3) {
            int count = 0;
            for (int i = y - btm_left; i <= y + up_right; i++) {
                gameBoard[x + btm_left - count][i] = BoardNutValue.LINE.getValue();
                count++;
            }
            numberOfLine++;
        }
        //check for creating line from up-left to btm-right
        if (up_left + btm_right + 1 >= 3) {
            int count = 0;
            for (int i = x - up_left; i <= x + btm_right; i++) {
                gameBoard[i][y - up_left + count] = BoardNutValue.LINE.getValue();
                count++;
            }
            numberOfLine++;
        }
        //number of lines
        return numberOfLine;
    }

    @Override
    public boolean isFinished() {
        int count = 0;
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                if (gameBoard[i][j] != BoardNutValue.EMPTY.getValue()) count++;
        return count == row * col;
    }

    @Override
    public int valueOf(int x, int y) {
        return gameBoard[x][y];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                sb.append(gameBoard[i][j]);
                sb.append(" | ");
            }
            sb.delete(sb.length() - 2, sb.length() - 1);
            sb.append('\n');
        }
        return sb.toString();
    }
}
